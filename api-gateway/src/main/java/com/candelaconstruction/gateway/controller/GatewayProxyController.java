package com.candelaconstruction.gateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@ConditionalOnProperty(name = "gateway.proxy.enabled", havingValue = "true")
public class GatewayProxyController {

    private final RestTemplate restTemplate;

    @Value("${services.project-service.url}")
    private String projectServiceUrl;

    @Value("${services.tendering-service.url}")
    private String tenderingServiceUrl;

    @Value("${services.operations-service.url}")
    private String operationsServiceUrl;

    @Value("${services.hr-news-service.url}")
    private String hrNewsServiceUrl;

    public GatewayProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/gateway/info")
    public ResponseEntity<Map<String, Object>> getGatewayInfo() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("service", "CandelaConstruction API Gateway");
        info.put("status", "UP");
        info.put("version", "1.0.0");
        info.put("routes", Map.of(
                "project-service", projectServiceUrl + " (/api/projects, /api/services, /api/fleet, /api/stats, /api/states)",
                "tendering-service", tenderingServiceUrl + " (/api/rfq, /api/calculator)",
                "operations-service", operationsServiceUrl + " (/api/scada, /api/hse)",
                "hr-news-service", hrNewsServiceUrl + " (/api/careers, /api/news)"
        ));
        return ResponseEntity.ok(info);
    }

    @GetMapping("/api/gateway/health")
    public ResponseEntity<Map<String, Object>> checkDownstreamHealth() {
        Map<String, Object> results = new LinkedHashMap<>();
        results.put("api-gateway", "UP");
        results.put("project-service", ping(projectServiceUrl + "/actuator/health"));
        results.put("tendering-service", ping(tenderingServiceUrl + "/actuator/health"));
        results.put("operations-service", ping(operationsServiceUrl + "/actuator/health"));
        results.put("hr-news-service", ping(hrNewsServiceUrl + "/actuator/health"));
        return ResponseEntity.ok(results);
    }

    private String ping(String url) {
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
            return res.getStatusCode().is2xxSuccessful() ? "UP" : "DEGRADED (" + res.getStatusCode() + ")";
        } catch (Exception e) {
            return "DOWN (" + e.getMessage() + ")";
        }
    }

    @RequestMapping(value = {
            "/api/projects/**",
            "/api/services/**",
            "/api/fleet/**",
            "/api/stats/**",
            "/api/states/**"
    })
    public ResponseEntity<byte[]> proxyProjectService(HttpServletRequest request,
                                                      @RequestBody(required = false) byte[] body) throws URISyntaxException, IOException {
        return forward(request, body, projectServiceUrl);
    }

    @RequestMapping(value = {
            "/api/rfq/**",
            "/api/calculator/**"
    })
    public ResponseEntity<byte[]> proxyTenderingService(HttpServletRequest request,
                                                        @RequestBody(required = false) byte[] body) throws URISyntaxException, IOException {
        return forward(request, body, tenderingServiceUrl);
    }

    @RequestMapping(value = {
            "/api/scada/**",
            "/api/hse/**"
    })
    public ResponseEntity<byte[]> proxyOperationsService(HttpServletRequest request,
                                                         @RequestBody(required = false) byte[] body) throws URISyntaxException, IOException {
        return forward(request, body, operationsServiceUrl);
    }

    @RequestMapping(value = {
            "/api/careers/**",
            "/api/news/**"
    })
    public ResponseEntity<byte[]> proxyHrNewsService(HttpServletRequest request,
                                                     @RequestBody(required = false) byte[] body) throws URISyntaxException, IOException {
        return forward(request, body, hrNewsServiceUrl);
    }

    private ResponseEntity<byte[]> forward(HttpServletRequest request, byte[] body, String targetBaseUrl) throws URISyntaxException {
        String path = request.getRequestURI();
        String query = request.getQueryString();
        String targetUrl = targetBaseUrl + path + (query != null ? "?" + query : "");

        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            if (!name.equalsIgnoreCase("host") && !name.equalsIgnoreCase("content-length")) {
                headers.addAll(name, Collections.list(request.getHeaders(name)));
            }
        }

        HttpEntity<byte[]> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(new URI(targetUrl), method, entity, byte[].class);
            HttpHeaders respHeaders = new HttpHeaders();
            response.getHeaders().forEach((k, v) -> {
                if (!k.equalsIgnoreCase(HttpHeaders.TRANSFER_ENCODING)) {
                    respHeaders.addAll(k, v);
                }
            });
            return new ResponseEntity<>(response.getBody(), respHeaders, response.getStatusCode());
        } catch (HttpStatusCodeException e) {
            HttpHeaders errHeaders = new HttpHeaders();
            e.getResponseHeaders().forEach((k, v) -> {
                if (!k.equalsIgnoreCase(HttpHeaders.TRANSFER_ENCODING)) {
                    errHeaders.addAll(k, v);
                }
            });
            return new ResponseEntity<>(e.getResponseBodyAsByteArray(), errHeaders, e.getStatusCode());
        } catch (Exception e) {
            String errorJson = String.format("{\"error\":\"Service Unavailable\",\"message\":\"%s\",\"target\":\"%s\"}",
                    e.getMessage(), targetUrl);
            HttpHeaders headersJson = new HttpHeaders();
            headersJson.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(errorJson.getBytes(), headersJson, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
