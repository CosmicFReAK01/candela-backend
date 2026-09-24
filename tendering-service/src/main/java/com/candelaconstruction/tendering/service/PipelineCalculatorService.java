package com.candelaconstruction.tendering.service;

import com.candelaconstruction.tendering.dto.CalculatorRequest;
import com.candelaconstruction.tendering.dto.CalculatorResponse;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class PipelineCalculatorService {

    private static class TerrainConfig {
        final double multiplier;
        final String note;
        TerrainConfig(double multiplier, String note) {
            this.multiplier = multiplier;
            this.note = note;
        }
    }

    private static final TerrainConfig[] TERRAINS = new TerrainConfig[] {
            new TerrainConfig(1.0, "Standard open-cut trenching in soft soil"),
            new TerrainConfig(1.55, "Controlled blasting or mechanical rock cutting"),
            new TerrainConfig(2.3, "Trenchless HDD / microtunnel installation"),
            new TerrainConfig(1.4, "De-watering, temporary causeways, sheet piling"),
            new TerrainConfig(1.7, "Traffic management, night pours, micro-tunnelling")
    };

    private static final double[] PRESSURE_FACTORS = new double[] { 1.0, 1.12, 1.3, 1.55 };

    public CalculatorResponse calculateEstimate(CalculatorRequest req) {
        double odInches = req.getDiameterInches();
        double wtMm = req.getWallThicknessMm();
        double lengthKm = req.getLengthKm();

        int tIdx = Math.max(0, Math.min(req.getTerrainIndex(), TERRAINS.length - 1));
        TerrainConfig terrain = TERRAINS[tIdx];

        int pIdx = Math.max(0, Math.min(req.getPressureIndex(), PRESSURE_FACTORS.length - 1));
        double pressFactor = PRESSURE_FACTORS[pIdx];

        double odMm = odInches * 25.4;
        double idMm = odMm - 2.0 * wtMm;

        // Steel Weight: W (kg/m) = pi * (OD - WT) * WT * 7850 / 1e6
        double steelKgPerM = Math.PI * (odMm - wtMm) * wtMm * 7850.0 / 1e6;
        double totalSteelTons = (steelKgPerM * lengthKm * 1000.0) / 1000.0;

        // Joints: 12.1m standard double-random joint length
        long joints = Math.round((lengthKm * 1000.0) / 12.1);

        // Water requirement in KL (m^3) with 15% margin
        double waterVolumeKL = Math.PI * Math.pow(idMm / 2000.0, 2) * lengthKm * 1000.0 * 1.15;

        // Base Cost per KM (Cr)
        double baseCostPerKm = odInches < 20 ? 3.2 : odInches < 36 ? 5.8 : 9.5;
        double totalCostCr = baseCostPerKm * lengthKm * terrain.multiplier * pressFactor;

        String grade = odInches >= 36 ? "API 5L X70 / X80 PSL2" :
                odInches >= 20 ? "API 5L X60 / X70 PSL2" : "API 5L X52 / X60";

        DecimalFormat dfInt = new DecimalFormat("#,##0");
        DecimalFormat dfCost = new DecimalFormat("0.0");

        CalculatorResponse res = new CalculatorResponse();
        res.setDiameterInches(odInches);
        res.setLengthKm(lengthKm);
        res.setSteelTonnage(dfInt.format(Math.round(totalSteelTons)));
        res.setWaterKL(dfInt.format(Math.round(waterVolumeKL)));
        res.setJoints(dfInt.format(joints));
        res.setCostCr(dfCost.format(totalCostCr));
        res.setGrade(grade);
        res.setMethod(terrain.note);
        res.setStandard("API 1104 / ASME B31.8 / OISD 226");

        return res;
    }
}
