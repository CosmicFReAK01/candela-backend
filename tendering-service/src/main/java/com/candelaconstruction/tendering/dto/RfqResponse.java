package com.candelaconstruction.tendering.dto;

import java.time.LocalDateTime;

public class RfqResponse {

    private boolean success;
    private String referenceNo;
    private String message;
    private String status;
    private String estimatedTurnaround;
    private LocalDateTime timestamp;

    public RfqResponse() {}

    public RfqResponse(boolean success, String referenceNo, String message, String status, String estimatedTurnaround) {
        this.success = success;
        this.referenceNo = referenceNo;
        this.message = message;
        this.status = status;
        this.estimatedTurnaround = estimatedTurnaround;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEstimatedTurnaround() { return estimatedTurnaround; }
    public void setEstimatedTurnaround(String estimatedTurnaround) { this.estimatedTurnaround = estimatedTurnaround; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
