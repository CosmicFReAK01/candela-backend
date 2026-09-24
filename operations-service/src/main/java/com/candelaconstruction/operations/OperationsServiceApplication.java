package com.candelaconstruction.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.candelaconstruction.operations.repository")
@EntityScan(basePackages = "com.candelaconstruction.operations.model")
public class OperationsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperationsServiceApplication.class, args);
    }
}
