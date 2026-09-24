package com.candelaconstruction.tendering.controller;

import com.candelaconstruction.tendering.dto.RfqRequest;
import com.candelaconstruction.tendering.dto.RfqResponse;
import com.candelaconstruction.tendering.model.RfqEnquiry;
import com.candelaconstruction.tendering.service.RfqService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rfq")
@CrossOrigin(origins = "*")
public class RfqController {

    private final RfqService rfqService;

    public RfqController(RfqService rfqService) {
        this.rfqService = rfqService;
    }

    @PostMapping
    public ResponseEntity<RfqResponse> submitRfq(@Valid @RequestBody RfqRequest request) {
        RfqResponse response = rfqService.createEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RfqEnquiry>> getAllEnquiries() {
        return ResponseEntity.ok(rfqService.getAllEnquiries());
    }

    @GetMapping("/{ref}")
    public ResponseEntity<RfqEnquiry> getEnquiryByRef(@PathVariable("ref") String ref) {
        return rfqService.getEnquiryByRef(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
