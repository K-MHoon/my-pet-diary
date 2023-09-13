package com.kmhoon.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kmhoon"})
public class DiaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiaryServiceApplication.class, args);
    }

}
