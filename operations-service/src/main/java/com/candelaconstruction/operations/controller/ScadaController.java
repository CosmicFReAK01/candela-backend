package com.candelaconstruction.operations.controller;

import com.candelaconstruction.operations.dto.TelemetrySnapshot;
import com.candelaconstruction.operations.model.LogEntry;
import com.candelaconstruction.operations.model.Station;
import com.candelaconstruction.operations.service.ScadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scada")
@CrossOrigin(origins = "*")
public class ScadaController {

    private final ScadaService scadaService;

    public ScadaController(ScadaService scadaService) {
        this.scadaService = scadaService;
    }

    @GetMapping("/telemetry")
    public ResponseEntity<TelemetrySnapshot> getTelemetry() {
        return ResponseEntity.ok(scadaService.getTelemetrySnapshot());
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getStations() {
        return ResponseEntity.ok(scadaService.getAllStations());
    }

    @PostMapping("/trip")
    public ResponseEntity<Map<String, Object>> tripStation(
            @RequestParam(name = "stationId", defaultValue = "SV-04") String stationId) {
        boolean success = scadaService.tripStation(stationId);
        return ResponseEntity.ok(Map.of(
                "success", success,
                "stationId", stationId,
                "status", "TRIPPED",
                "message", "ESD Emergency trip command successfully executed on station " + stationId
        ));
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> resetStation(
            @RequestParam(name = "stationId", defaultValue = "SV-04") String stationId) {
        boolean success = scadaService.resetStation(stationId);
        return ResponseEntity.ok(Map.of(
                "success", success,
                "stationId", stationId,
                "status", "ONLINE",
                "message", "Station " + stationId + " re-pressurized and restored to ONLINE"
        ));
    }

    @GetMapping("/logs")
    public ResponseEntity<List<LogEntry>> getLogs() {
        return ResponseEntity.ok(scadaService.getRecentLogs());
    }
}
