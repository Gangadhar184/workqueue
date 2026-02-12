package com.example.workqueue.dto;

public record MetricResponse(
        long totalJobsInQueue,
        long jobsDone,
        long jobsFailed
) {

}
