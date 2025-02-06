package com.example.todoexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoExpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoExpertApplication.class, args);
    }

}
