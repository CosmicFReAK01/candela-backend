package com.candelaconstruction.tendering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.candelaconstruction.tendering.repository")
@EntityScan(basePackages = "com.candelaconstruction.tendering.model")
public class TenderingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenderingServiceApplication.class, args);
    }
}
