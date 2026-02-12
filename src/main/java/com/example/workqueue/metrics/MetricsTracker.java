package com.example.workqueue.metrics;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class MetricsTracker {
    private final AtomicLong jobsDone = new AtomicLong();
    private final AtomicLong jobsFailed = new AtomicLong();
    private final AtomicLong totalJobsInQueue = new AtomicLong();

    public void incrementDone() {
        jobsDone.incrementAndGet();
    }
    public void incrementFailed() {
        jobsFailed.incrementAndGet();
    }
    public void setQueueSize(long size){
        totalJobsInQueue.set(size);
    }
    public long getJobsDone(){
        return jobsDone.get();
    }
    public long getJobsFailed(){
        return jobsFailed.get();
    }
    public long getTotalJobsInQueue(){
        return totalJobsInQueue.get();
    }
}
