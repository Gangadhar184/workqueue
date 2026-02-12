package com.example.workqueue.models;

import java.util.Map;

public record Task(
        String type,
        Map<String, Object> payload,
        int retries
) {
}
