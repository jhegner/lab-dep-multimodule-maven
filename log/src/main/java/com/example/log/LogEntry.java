package com.example.log;

import java.time.Instant;

public class LogEntry {

    private final String level;
    private final String message;
    private final Instant timestamp;

    public LogEntry(String level, String message, Instant timestamp) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
