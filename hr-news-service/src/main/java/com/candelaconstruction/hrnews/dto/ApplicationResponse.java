package com.candelaconstruction.hrnews.dto;

import java.time.LocalDateTime;

public class ApplicationResponse {

    private boolean success;
    private String applicationRef;
    private String message;
    private String positionTitle;
    private LocalDateTime timestamp;

    public ApplicationResponse() {}

    public ApplicationResponse(boolean success, String applicationRef, String message, String positionTitle) {
        this.success = success;
        this.applicationRef = applicationRef;
        this.message = message;
        this.positionTitle = positionTitle;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getApplicationRef() { return applicationRef; }
    public void setApplicationRef(String applicationRef) { this.applicationRef = applicationRef; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPositionTitle() { return positionTitle; }
    public void setPositionTitle(String positionTitle) { this.positionTitle = positionTitle; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
