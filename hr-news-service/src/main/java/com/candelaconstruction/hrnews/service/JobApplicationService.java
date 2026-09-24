package com.candelaconstruction.hrnews.service;

import com.candelaconstruction.hrnews.dto.ApplicationRequest;
import com.candelaconstruction.hrnews.dto.ApplicationResponse;
import com.candelaconstruction.hrnews.model.JobApplication;
import com.candelaconstruction.hrnews.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final SecureRandom random = new SecureRandom();

    public JobApplicationService(JobApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationResponse submitApplication(ApplicationRequest req) {
        String ref = "APP-2026-" + (1000 + random.nextInt(9000));

        JobApplication app = new JobApplication();
        app.setApplicationRef(ref);
        app.setPositionTitle(req.getPositionTitle());
        app.setName(req.getName());
        app.setEmail(req.getEmail());
        app.setPhone(req.getPhone());
        app.setExperience(req.getExperience());
        app.setLocation(req.getLocation());
        app.setStatus("RECEIVED");

        applicationRepository.save(app);

        return new ApplicationResponse(
                true,
                ref,
                "Your application has been received. Our HR team will review your qualifications within 3 business days.",
                req.getPositionTitle()
        );
    }

    public List<JobApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
}
