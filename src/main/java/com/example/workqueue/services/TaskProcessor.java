package com.example.workqueue.services;

import com.example.workqueue.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskProcessor {

    private static final Logger log = LoggerFactory.getLogger(TaskProcessor.class);

    public void process(Task task) throws Exception {
        log.info("Starting processing task. Type: {}", task.type());

        if (task.payload() == null) {
            log.error("Task payload is null for type: {}", task.type());
            throw new IllegalArgumentException("Payload is empty");
        }

        switch (task.type()) {
            case "send_email" -> {
                log.info("Processing send_email task. Payload: {}", task.payload());
                Thread.sleep(2000);
                log.info("Sending email to {}", task.payload().get("to"));
            }
            case "resize_image" -> {
                log.info("Processing resize_image task");
                System.out.println("Resizing Image");
            }
            case "generate_pdf" -> {
                log.info("Processing generate_pdf task");
                System.out.println("Generating pdf");
            }
            default -> {
                log.error("Unsupported task type: {}", task.type());
                throw new IllegalArgumentException("Unsupported task type");
            }
        }

        log.info("Finished processing task. Type: {}", task.type());
    }
}
