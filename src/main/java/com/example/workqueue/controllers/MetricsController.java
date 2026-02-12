package com.example.workqueue.controllers;

import com.example.workqueue.dto.MetricResponse;
import com.example.workqueue.metrics.MetricsTracker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {
    private final MetricsTracker metrics;

    public MetricsController(MetricsTracker metrics) {
        this.metrics = metrics;
    }

    @GetMapping("/metrics")
    public MetricResponse getMetrics() {
        return new MetricResponse(
                metrics.getTotalJobsInQueue(),
                metrics.getJobsDone(),
                metrics.getJobsFailed()
        );
    }
}


