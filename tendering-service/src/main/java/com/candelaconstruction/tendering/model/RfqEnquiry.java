package com.candelaconstruction.tendering.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rfq_enquiries")
public class RfqEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String referenceNo;

    @Column(nullable = false)
    private String companyName;

    private String contactPerson;

    @Column(nullable = false)
    private String email;

    private String phone;
    private String location;
    private String approxLength;
    private String diameter;
    private String projectType;

    @Column(length = 2000)
    private String description;

    private String status;
    private LocalDateTime createdAt;

    public RfqEnquiry() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING_REVIEW";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getApproxLength() { return approxLength; }
    public void setApproxLength(String approxLength) { this.approxLength = approxLength; }

    public String getDiameter() { return diameter; }
    public void setDiameter(String diameter) { this.diameter = diameter; }

    public String getProjectType() { return projectType; }
    public void setProjectType(String projectType) { this.projectType = projectType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
