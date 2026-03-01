package com.example.log;

public class JsonLogFormatter {

    public String format(LogEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("LogEntry must not be null");
        }
        return String.format(
                "{\"level\":\"%s\",\"message\":\"%s\",\"timestamp\":\"%s\"}",
                escape(entry.getLevel()),
                escape(entry.getMessage()),
                entry.getTimestamp()
        );
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"");
    }
}
