package com.candelaconstruction.tendering.dto;

public class CalculatorResponse {

    private String steelTonnage;
    private String waterKL;
    private String joints;
    private String costCr;
    private String grade;
    private String method;
    private String standard;
    private double lengthKm;
    private double diameterInches;

    public CalculatorResponse() {}

    public String getSteelTonnage() { return steelTonnage; }
    public void setSteelTonnage(String steelTonnage) { this.steelTonnage = steelTonnage; }

    public String getWaterKL() { return waterKL; }
    public void setWaterKL(String waterKL) { this.waterKL = waterKL; }

    public String getJoints() { return joints; }
    public void setJoints(String joints) { this.joints = joints; }

    public String getCostCr() { return costCr; }
    public void setCostCr(String costCr) { this.costCr = costCr; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStandard() { return standard; }
    public void setStandard(String standard) { this.standard = standard; }

    public double getLengthKm() { return lengthKm; }
    public void setLengthKm(double lengthKm) { this.lengthKm = lengthKm; }

    public double getDiameterInches() { return diameterInches; }
    public void setDiameterInches(double diameterInches) { this.diameterInches = diameterInches; }
}
