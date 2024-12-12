package com.test.spring_demo.student;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLoggerService {

    private static final String TOPIC = "topic-test";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaLoggerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}

