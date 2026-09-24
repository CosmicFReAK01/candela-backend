package com.candelaconstruction.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = "com.candelaconstruction")
@EntityScan(basePackages = "com.candelaconstruction")
@EnableJpaRepositories(basePackages = "com.candelaconstruction")
@RestController
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

    @GetMapping("/api/gateway/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "api-gateway", "UP",
                "project-service", "UP",
                "tendering-service", "UP",
                "operations-service", "UP",
                "hr-news-service", "UP"
        );
    }

    @GetMapping("/api/gateway/info")
    public Map<String, Object> info() {
        return Map.of(
                "service", "CandelaConstruction Unified Cloud Microservices Cluster",
                "status", "UP",
                "version", "1.0.0"
        );
    }
}
