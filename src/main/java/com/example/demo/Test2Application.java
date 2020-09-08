package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.demo")
@EntityScan("com.example.demo")
@SpringBootApplication
public class Test2Application {

    public static void main(String[] args) {
                SpringApplication.run(Test2Application.class, args);
    }

}

