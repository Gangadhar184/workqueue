package com.example.workqueue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record TaskRequest(
        @NotBlank
        String type,
        @NotNull
        Map<String, Object> payload,
        int retries
) {
}
