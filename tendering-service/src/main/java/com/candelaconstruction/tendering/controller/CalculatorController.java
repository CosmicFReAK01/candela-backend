package com.candelaconstruction.tendering.controller;

import com.candelaconstruction.tendering.dto.CalculatorRequest;
import com.candelaconstruction.tendering.dto.CalculatorResponse;
import com.candelaconstruction.tendering.service.PipelineCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final PipelineCalculatorService calculatorService;

    public CalculatorController(PipelineCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/estimate")
    public ResponseEntity<CalculatorResponse> calculateEstimate(@RequestBody CalculatorRequest request) {
        CalculatorResponse response = calculatorService.calculateEstimate(request);
        return ResponseEntity.ok(response);
    }
}
