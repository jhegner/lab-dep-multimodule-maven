package com.example.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JsonLogFormatterTest {

    private JsonLogFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new JsonLogFormatter();
    }

    @Test
    void format_infoEntry_returnsValidJson() {
        Instant ts = Instant.parse("2024-01-15T10:00:00Z");
        LogEntry entry = new LogEntry("INFO", "Application started", ts);

        String json = formatter.format(entry);

        assertEquals("{\"level\":\"INFO\",\"message\":\"Application started\",\"timestamp\":\"2024-01-15T10:00:00Z\"}", json);
    }

    @Test
    void format_errorEntry_returnsValidJson() {
        Instant ts = Instant.parse("2024-01-15T10:05:00Z");
        LogEntry entry = new LogEntry("ERROR", "Something went wrong", ts);

        String json = formatter.format(entry);

        assertTrue(json.contains("\"level\":\"ERROR\""));
        assertTrue(json.contains("\"message\":\"Something went wrong\""));
    }

    @Test
    void format_messageWithQuotes_escapesCorrectly() {
        Instant ts = Instant.parse("2024-01-15T10:00:00Z");
        LogEntry entry = new LogEntry("WARN", "Value is \"null\"", ts);

        String json = formatter.format(entry);

        assertTrue(json.contains("\\\"null\\\""));
    }

    @Test
    void format_nullEntry_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> formatter.format(null));
    }
}
