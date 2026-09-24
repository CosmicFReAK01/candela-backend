package com.candelaconstruction.operations;

import com.candelaconstruction.operations.dto.TelemetrySnapshot;
import com.candelaconstruction.operations.model.Station;
import com.candelaconstruction.operations.service.ScadaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OperationsServiceApplication.class)
class OperationsServiceApplicationTests {

    @Autowired
    private ScadaService scadaService;

    @Test
    void testScadaStationTelemetryAndTrip() {
        TelemetrySnapshot snapshot = scadaService.getTelemetrySnapshot();
        assertNotNull(snapshot);
        assertEquals(4, snapshot.getStations().size());
        assertFalse(snapshot.isEsdActive());

        // Trip SV-04
        boolean tripped = scadaService.tripStation("SV-04");
        assertTrue(tripped);

        Station sv04 = scadaService.getStation("SV-04");
        assertEquals("TRIPPED", sv04.getStatus());
        assertEquals(0.0, sv04.getPressure());

        // Reset
        boolean reset = scadaService.resetStation("SV-04");
        assertTrue(reset);
        assertEquals("ONLINE", scadaService.getStation("SV-04").getStatus());
    }
}
