package com.candelaconstruction.operations.model;

public class LogEntry {

    private String time;
    private String text;
    private String type; // info, warn, error, success

    public LogEntry() {}

    public LogEntry(String time, String text, String type) {
        this.time = time;
        this.text = text;
        this.type = type;
    }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
