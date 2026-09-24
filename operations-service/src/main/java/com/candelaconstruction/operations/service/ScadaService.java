package com.candelaconstruction.operations.service;

import com.candelaconstruction.operations.dto.TelemetrySnapshot;
import com.candelaconstruction.operations.model.LogEntry;
import com.candelaconstruction.operations.model.Station;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ScadaService {

    private final Map<String, Station> stationMap = new ConcurrentHashMap<>();
    private final List<LogEntry> logs = new CopyOnWriteArrayList<>();
    private volatile double flowRate = 82.5;
    private volatile boolean esdActive = false;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ScadaService() {
        initStations();
        addLog("SCADA Industrial Automation Service Initialized — Real-time telemetry online", "success");
        addLog("Sectionalizing Valve Stations SV-01 through SV-04 telemetry synchronized", "info");
    }

    private void initStations() {
        stationMap.put("SV-01", new Station("SV-01", "Origin Compressor Station", "KP 0.0", 98.2, 98.2, "ONLINE"));
        stationMap.put("SV-02", new Station("SV-02", "Intermediate Block Valve #1", "KP 142.5", 94.6, 94.6, "ONLINE"));
        stationMap.put("SV-03", new Station("SV-03", "River Crossing SV Station", "KP 286.0", 91.3, 91.3, "ONLINE"));
        stationMap.put("SV-04", new Station("SV-04", "Terminus Delivery Station", "KP 422.8", 87.8, 87.8, "ONLINE"));
    }

    public TelemetrySnapshot getTelemetrySnapshot() {
        List<Station> stationList = new ArrayList<>(stationMap.values());
        stationList.sort(Comparator.comparing(Station::getId));
        int alarms = esdActive ? 1 : 0;
        String gridStatus = esdActive ? "ESD TRIPPED / CONTAINED" : "NOMINAL OPERATING PRESSURE";
        return new TelemetrySnapshot(stationList, flowRate, esdActive, alarms, gridStatus);
    }

    public List<Station> getAllStations() {
        List<Station> list = new ArrayList<>(stationMap.values());
        list.sort(Comparator.comparing(Station::getId));
        return list;
    }

    public Station getStation(String id) {
        return stationMap.get(id);
    }

    public synchronized boolean tripStation(String stationId) {
        Station s = stationMap.get(stationId);
        if (s == null) return false;

        esdActive = true;
        s.setStatus("TRIPPED");
        s.setPressure(0.0);

        addLog("⚡ EMERGENCY SHUTDOWN (ESD): Station " + stationId + " (" + s.getName() + ") valve TRIP activated", "error");
        addLog("Actuator driving to FAIL-SAFE CLOSED position. Isolation confirmed at " + s.getKp(), "warn");

        return true;
    }

    public synchronized boolean resetStation(String stationId) {
        Station s = stationMap.get(stationId);
        if (s == null) return false;

        s.setStatus("ONLINE");
        s.setPressure(s.getBasePressure());
        esdActive = false;

        addLog("Re-pressurization sequence authorized for " + stationId + " (" + s.getName() + ")", "info");
        addLog("Pneumatic valve actuator reset to OPEN. All stations reporting ONLINE", "success");

        return true;
    }

    public List<LogEntry> getRecentLogs() {
        int size = logs.size();
        int from = Math.max(0, size - 40);
        return new ArrayList<>(logs.subList(from, size));
    }

    public void addLog(String text, String type) {
        String time = LocalTime.now().format(timeFormatter);
        logs.add(new LogEntry(time, text, type));
        if (logs.size() > 100) {
            logs.remove(0);
        }
    }

    @Scheduled(fixedRate = 2500)
    public void simulateTelemetryJitter() {
        for (Station s : stationMap.values()) {
            if ("TRIPPED".equals(s.getStatus())) {
                s.setPressure(0.0);
            } else {
                double delta = (ThreadLocalRandom.current().nextDouble() - 0.5) * 0.8;
                double newPressure = Math.round((s.getBasePressure() + delta) * 10.0) / 10.0;
                s.setPressure(newPressure);
            }
        }
        double flowDelta = (ThreadLocalRandom.current().nextDouble() - 0.5) * 1.2;
        flowRate = Math.round((flowRate + flowDelta) * 10.0) / 10.0;
        if (flowRate < 75.0) flowRate = 78.0;
        if (flowRate > 90.0) flowRate = 86.0;
    }
}
