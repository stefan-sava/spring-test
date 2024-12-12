package com.test.spring_demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    private final KafkaLoggerService kafkaLoggerService;

    public StudentConfig(KafkaLoggerService kafkaLoggerService) {
        this.kafkaLoggerService = kafkaLoggerService;
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariana = new Student("Mariana",
                    LocalDate.of(2000, 10, 14),
                    "mariana@gmail.com");
            Student alex = new Student("Alex",
                    LocalDate.of(2001, 10, 14),
                    "alex@gmail.com");

            repository.saveAll(List.of(mariana, alex));

            // Trimite log-uri în Kafka
            kafkaLoggerService.sendLog("Student registered: " + mariana);
            kafkaLoggerService.sendLog("Student registered: " + alex);
        };
    }
}
