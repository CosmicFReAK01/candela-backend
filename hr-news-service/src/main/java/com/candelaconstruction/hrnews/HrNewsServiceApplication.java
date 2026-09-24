package com.candelaconstruction.hrnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.candelaconstruction.hrnews.repository")
@EntityScan(basePackages = "com.candelaconstruction.hrnews.model")
public class HrNewsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrNewsServiceApplication.class, args);
    }
}
