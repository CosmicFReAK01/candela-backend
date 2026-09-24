package com.candelaconstruction.project.controller;

import com.candelaconstruction.project.model.StateFootprint;
import com.candelaconstruction.project.repository.StateFootprintRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class StatsController {

    private final StateFootprintRepository stateRepository;

    public StatsController(StateFootprintRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping("/api/stats")
    public ResponseEntity<Map<String, Object>> getCompanyStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalKmConstructed", "2,400+ KM");
        stats.put("maxDiameterInches", "48\" (1,219mm)");
        stats.put("safeManHoursMillions", "25M+ Hours");
        stats.put("activeSpreads", "8 Spreads");
        stats.put("hddCrossingsRecord", "2,140 M Single-Shot");
        stats.put("cgdConnections", "60,000+ PNG");
        stats.put("operatingStates", 8);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/api/states")
    public ResponseEntity<List<StateFootprint>> getStates() {
        return ResponseEntity.ok(stateRepository.findAll());
    }
}
