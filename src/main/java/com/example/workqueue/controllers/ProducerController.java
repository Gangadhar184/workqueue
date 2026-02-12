package com.example.workqueue.controllers;

import com.example.workqueue.dto.TaskRequest;
import com.example.workqueue.models.Task;
import com.example.workqueue.services.WorkQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final WorkQueueService  queueService;

    public ProducerController(WorkQueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/enqueue")
    public ResponseEntity<String> enqueue(@RequestBody TaskRequest request) throws Exception {
        Task task = new Task(
                request.type(),
                request.payload(),
                request.retries()
        );
        queueService.enqueue(task);
        return ResponseEntity.ok(
                "Task of type '" + request.type() + "' added to queue"
        );
    }
}
