package com.candelaconstruction.hrnews;

import com.candelaconstruction.hrnews.dto.ApplicationRequest;
import com.candelaconstruction.hrnews.dto.ApplicationResponse;
import com.candelaconstruction.hrnews.repository.CorporateNewsRepository;
import com.candelaconstruction.hrnews.repository.JobPositionRepository;
import com.candelaconstruction.hrnews.service.JobApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HrNewsServiceApplication.class)
class HrNewsServiceApplicationTests {

    @Autowired
    private JobPositionRepository jobRepository;

    @Autowired
    private CorporateNewsRepository newsRepository;

    @Autowired
    private JobApplicationService applicationService;

    @Test
    void testCareersAndNewsSeeding() {
        assertTrue(jobRepository.count() > 0, "Jobs should be seeded");
        assertTrue(newsRepository.count() > 0, "Corporate news should be seeded");
    }

    @Test
    void testJobApplicationSubmission() {
        ApplicationRequest req = new ApplicationRequest();
        req.setPositionTitle("Senior Cross-Country Pipeline Engineer");
        req.setName("Sunil Verma");
        req.setEmail("sunil.verma@example.com");
        req.setPhone("+91 99887 76655");
        req.setExperience("9 Years");
        req.setLocation("Bihar");

        ApplicationResponse res = applicationService.submitApplication(req);
        assertTrue(res.isSuccess());
        assertNotNull(res.getApplicationRef());
        assertTrue(res.getApplicationRef().startsWith("APP-2026-"));
    }
}
