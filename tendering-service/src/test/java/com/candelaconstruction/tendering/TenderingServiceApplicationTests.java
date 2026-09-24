package com.candelaconstruction.tendering;

import com.candelaconstruction.tendering.dto.CalculatorRequest;
import com.candelaconstruction.tendering.dto.CalculatorResponse;
import com.candelaconstruction.tendering.dto.RfqRequest;
import com.candelaconstruction.tendering.dto.RfqResponse;
import com.candelaconstruction.tendering.service.PipelineCalculatorService;
import com.candelaconstruction.tendering.service.RfqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TenderingServiceApplication.class)
class TenderingServiceApplicationTests {

    @Autowired
    private RfqService rfqService;

    @Autowired
    private PipelineCalculatorService calculatorService;

    @Test
    void testRfqCreation() {
        RfqRequest req = new RfqRequest();
        req.setCompanyName("GAIL India Limited");
        req.setContactPerson("R. K. Sharma");
        req.setEmail("rksharma@gail.co.in");
        req.setPhone("+91 98765 43210");
        req.setDiameter("36 inch");
        req.setApproxLength("150 km");
        req.setProjectType("Cross Country Pipeline");

        RfqResponse res = rfqService.createEnquiry(req);
        assertTrue(res.isSuccess());
        assertNotNull(res.getReferenceNo());
        assertTrue(res.getReferenceNo().startsWith("RFQ-2026-"));
    }

    @Test
    void testEngineeringCostCalculation() {
        CalculatorRequest req = new CalculatorRequest();
        req.setDiameterInches(24.0);
        req.setWallThicknessMm(15.9);
        req.setLengthKm(100.0);
        req.setTerrainIndex(0);
        req.setPressureIndex(2);

        CalculatorResponse res = calculatorService.calculateEstimate(req);
        assertNotNull(res.getSteelTonnage());
        assertNotNull(res.getCostCr());
        assertNotNull(res.getJoints());
        assertEquals("API 5L X60 / X70 PSL2", res.getGrade());
    }
}
