package com.candelaconstruction.hrnews.controller;

import com.candelaconstruction.hrnews.dto.ApplicationRequest;
import com.candelaconstruction.hrnews.dto.ApplicationResponse;
import com.candelaconstruction.hrnews.model.JobApplication;
import com.candelaconstruction.hrnews.model.JobPosition;
import com.candelaconstruction.hrnews.repository.JobPositionRepository;
import com.candelaconstruction.hrnews.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin(origins = "*")
public class CareersController {

    private final JobPositionRepository positionRepository;
    private final JobApplicationService applicationService;

    public CareersController(JobPositionRepository positionRepository, JobApplicationService applicationService) {
        this.positionRepository = positionRepository;
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<JobPosition>> getCareers(
            @RequestParam(name = "department", required = false) String department) {
        if (department != null && !department.equalsIgnoreCase("all")) {
            return ResponseEntity.ok(positionRepository.findByDepartmentIgnoreCase(department));
        }
        return ResponseEntity.ok(positionRepository.findAll());
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> apply(@Valid @RequestBody ApplicationRequest request) {
        ApplicationResponse res = applicationService.submitApplication(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> getApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }
}
