package com.valtech.mobility;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}