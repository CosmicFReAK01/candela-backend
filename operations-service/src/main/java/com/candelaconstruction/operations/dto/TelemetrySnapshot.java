package com.candelaconstruction.operations.dto;

import com.candelaconstruction.operations.model.Station;

import java.util.List;

public class TelemetrySnapshot {

    private List<Station> stations;
    private double flowRate;
    private boolean esdActive;
    private int activeAlarmCount;
    private String gridStatus;

    public TelemetrySnapshot() {}

    public TelemetrySnapshot(List<Station> stations, double flowRate, boolean esdActive, int activeAlarmCount, String gridStatus) {
        this.stations = stations;
        this.flowRate = flowRate;
        this.esdActive = esdActive;
        this.activeAlarmCount = activeAlarmCount;
        this.gridStatus = gridStatus;
    }

    public List<Station> getStations() { return stations; }
    public void setStations(List<Station> stations) { this.stations = stations; }

    public double getFlowRate() { return flowRate; }
    public void setFlowRate(double flowRate) { this.flowRate = flowRate; }

    public boolean isEsdActive() { return esdActive; }
    public void setEsdActive(boolean esdActive) { this.esdActive = esdActive; }

    public int getActiveAlarmCount() { return activeAlarmCount; }
    public void setActiveAlarmCount(int activeAlarmCount) { this.activeAlarmCount = activeAlarmCount; }

    public String getGridStatus() { return gridStatus; }
    public void setGridStatus(String gridStatus) { this.gridStatus = gridStatus; }
}
