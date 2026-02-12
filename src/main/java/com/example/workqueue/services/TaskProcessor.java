package com.example.workqueue.services;

import com.example.workqueue.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskProcessor {
    public void process(Task task) throws Exception {
        if (task.payload() == null) {
            throw new IllegalArgumentException("Payload is empty");
        }
        switch (task.type()) {
            case "send_email" -> {
                Thread.sleep(2000);
                System.out.println("sending email to " + task.payload().get("to"));
            }
            case "resize_image" -> {
                System.out.println("Resizing Image");
            }
            case "generate_pdf" -> {
                System.out.println("Generating pdf");
            }
            default -> throw new IllegalArgumentException("Unsupport task type");
        }
    }
}
