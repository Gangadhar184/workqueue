package com.example.workqueue.worker;

import com.example.workqueue.services.WorkerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerRunner {
    private final WorkerService workerService;

    public WorkerRunner(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Value("${workqueue.worker.threads}")
    private int threadCount;

    @PostConstruct
    public void startWorkers() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(workerService::runWorker);
        }
    }
}
