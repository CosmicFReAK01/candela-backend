package com.candelaconstruction.operations.controller;

import com.candelaconstruction.operations.dto.HseMetricsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/hse")
@CrossOrigin(origins = "*")
public class HseController {

    @GetMapping
    public ResponseEntity<HseMetricsDto> getHseMetrics() {
        HseMetricsDto hse = new HseMetricsDto();
        hse.setSafeManHours("25,480,000+");
        hse.setLostTimeInjuryRate(0.00);
        hse.setEnvironmentalIncidents(0);
        hse.setConsecutiveSafeDays(1420);
        hse.setCertifications(Arrays.asList(
                "ISO 9001:2015 Quality Management Systems",
                "ISO 14001:2015 Environmental Management Systems",
                "ISO 45001:2018 Occupational Health & Safety Management",
                "OHSAS 18001 Occupational Safety Assessment",
                "PNGRB T4S Pipeline Code Compliance"
        ));
        hse.setSafetyPillars(Arrays.asList(
                Map.of("title", "Zero Harm Culture", "desc", "Every worker empowered with Stop-Work Authority."),
                Map.of("title", "100% PAUT / NDT", "desc", "Zero tolerance on weld defect tolerances before backfill."),
                Map.of("title", "Eco-Restoration", "desc", "Topsoil preservation and agricultural replanting protocols.")
        ));

        return ResponseEntity.ok(hse);
    }
}
