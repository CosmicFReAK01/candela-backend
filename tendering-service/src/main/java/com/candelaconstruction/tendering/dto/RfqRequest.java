package com.candelaconstruction.tendering.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RfqRequest {

    @NotBlank(message = "Company Name is required")
    private String companyName;

    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    private String email;

    private String phone;
    private String location;
    private String approxLength;
    private String diameter;
    private String projectType;
    private String description;

    public RfqRequest() {}

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
}
