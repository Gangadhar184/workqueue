package com.example.workqueue.services;

import com.example.workqueue.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkQueueService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public WorkQueueService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${workqueue.redis-queue-name}")
    private String queueName;

    public void enqueue(Task task) throws Exception {
        String json = objectMapper.writeValueAsString(task);
        redisTemplate.opsForList().rightPush(queueName, json);
    }


}
