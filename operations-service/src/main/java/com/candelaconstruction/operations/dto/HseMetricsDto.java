package com.candelaconstruction.operations.dto;

import java.util.List;
import java.util.Map;

public class HseMetricsDto {

    private String safeManHours;
    private double lostTimeInjuryRate;
    private int environmentalIncidents;
    private int consecutiveSafeDays;
    private List<String> certifications;
    private List<Map<String, String>> safetyPillars;

    public HseMetricsDto() {}

    public String getSafeManHours() { return safeManHours; }
    public void setSafeManHours(String safeManHours) { this.safeManHours = safeManHours; }

    public double getLostTimeInjuryRate() { return lostTimeInjuryRate; }
    public void setLostTimeInjuryRate(double lostTimeInjuryRate) { this.lostTimeInjuryRate = lostTimeInjuryRate; }

    public int getEnvironmentalIncidents() { return environmentalIncidents; }
    public void setEnvironmentalIncidents(int environmentalIncidents) { this.environmentalIncidents = environmentalIncidents; }

    public int getConsecutiveSafeDays() { return consecutiveSafeDays; }
    public void setConsecutiveSafeDays(int consecutiveSafeDays) { this.consecutiveSafeDays = consecutiveSafeDays; }

    public List<String> getCertifications() { return certifications; }
    public void setCertifications(List<String> certifications) { this.certifications = certifications; }

    public List<Map<String, String>> getSafetyPillars() { return safetyPillars; }
    public void setSafetyPillars(List<Map<String, String>> safetyPillars) { this.safetyPillars = safetyPillars; }
}
