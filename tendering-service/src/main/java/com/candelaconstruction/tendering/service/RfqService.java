package com.candelaconstruction.tendering.service;

import com.candelaconstruction.tendering.dto.RfqRequest;
import com.candelaconstruction.tendering.dto.RfqResponse;
import com.candelaconstruction.tendering.model.RfqEnquiry;
import com.candelaconstruction.tendering.repository.RfqRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class RfqService {

    private final RfqRepository rfqRepository;
    private final SecureRandom random = new SecureRandom();

    public RfqService(RfqRepository rfqRepository) {
        this.rfqRepository = rfqRepository;
    }

    public RfqResponse createEnquiry(RfqRequest request) {
        String referenceNo = "RFQ-2026-" + (1000 + random.nextInt(9000));

        RfqEnquiry entity = new RfqEnquiry();
        entity.setReferenceNo(referenceNo);
        entity.setCompanyName(request.getCompanyName());
        entity.setContactPerson(request.getContactPerson());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setLocation(request.getLocation());
        entity.setApproxLength(request.getApproxLength());
        entity.setDiameter(request.getDiameter());
        entity.setProjectType(request.getProjectType());
        entity.setDescription(request.getDescription());
        entity.setStatus("PENDING_REVIEW");

        rfqRepository.save(entity);

        return new RfqResponse(
                true,
                referenceNo,
                "Request for Quotation successfully registered with CandelaConstruction Tendering Division.",
                "PENDING_REVIEW",
                "48 Hours Commercial Review"
        );
    }

    public List<RfqEnquiry> getAllEnquiries() {
        return rfqRepository.findAll();
    }

    public Optional<RfqEnquiry> getEnquiryByRef(String ref) {
        return rfqRepository.findByReferenceNo(ref);
    }
}
