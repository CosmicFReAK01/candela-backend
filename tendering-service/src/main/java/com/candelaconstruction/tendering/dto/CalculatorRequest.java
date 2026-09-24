package com.candelaconstruction.tendering.dto;

public class CalculatorRequest {

    private double diameterInches = 24.0;
    private double wallThicknessMm = 15.9;
    private double lengthKm = 100.0;
    private int terrainIndex = 0; // 0: Alluvial, 1: Rocky, 2: Water crossing HDD, 3: Marshy, 4: Urban
    private int pressureIndex = 2; // 0: Class 150, 1: Class 300, 2: Class 600, 3: Class 900

    public CalculatorRequest() {}

    public double getDiameterInches() { return diameterInches; }
    public void setDiameterInches(double diameterInches) { this.diameterInches = diameterInches; }

    public double getWallThicknessMm() { return wallThicknessMm; }
    public void setWallThicknessMm(double wallThicknessMm) { this.wallThicknessMm = wallThicknessMm; }

    public double getLengthKm() { return lengthKm; }
    public void setLengthKm(double lengthKm) { this.lengthKm = lengthKm; }

    public int getTerrainIndex() { return terrainIndex; }
    public void setTerrainIndex(int terrainIndex) { this.terrainIndex = terrainIndex; }

    public int getPressureIndex() { return pressureIndex; }
    public void setPressureIndex(int pressureIndex) { this.pressureIndex = pressureIndex; }
}
