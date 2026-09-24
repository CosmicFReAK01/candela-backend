package com.candelaconstruction.operations.model;

public class Station {

    private String id;
    private String name;
    private String kp;
    private double basePressure;
    private double pressure;
    private String status; // ONLINE, TRIPPED, ISOLATED

    public Station() {}

    public Station(String id, String name, String kp, double basePressure, double pressure, String status) {
        this.id = id;
        this.name = name;
        this.kp = kp;
        this.basePressure = basePressure;
        this.pressure = pressure;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKp() { return kp; }
    public void setKp(String kp) { this.kp = kp; }

    public double getBasePressure() { return basePressure; }
    public void setBasePressure(double basePressure) { this.basePressure = basePressure; }

    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
