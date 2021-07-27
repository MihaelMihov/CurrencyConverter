package com.example.demo.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo.*")
public class BudgetOrganizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BudgetOrganizerApplication.class, args);
    }
}
