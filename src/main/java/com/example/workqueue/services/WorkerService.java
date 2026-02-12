package com.example.workqueue.services;

import com.example.workqueue.metrics.MetricsTracker;
import com.example.workqueue.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class WorkerService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final TaskProcessor processor;
    private final MetricsTracker metrics;

    public WorkerService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper, TaskProcessor processor, MetricsTracker metrics) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.processor = processor;
        this.metrics = metrics;
    }

    @Value("${workqueue.redis-queue-name}")
    private String queueName;

    public void runWorker() {
        while (true) {
            try {
                String json = redisTemplate.opsForList().leftPop(queueName);
                if (json == null) {
                    Thread.sleep(1000);
                    continue;
                }
                Task task = objectMapper.readValue(json, Task.class);
                try {
                    processor.process(task);
                    metrics.incrementDone();
                }catch (Exception e){
                    metrics.incrementFailed();
                }
                Long size = redisTemplate.opsForList().size(queueName);
                metrics.setQueueSize(size != null ? size : 0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
