package com.example.pkp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.pkp")
@EntityScan("com.example.pkp")
@SpringBootApplication
public class PkpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PkpApplication.class, args);
    }

}
