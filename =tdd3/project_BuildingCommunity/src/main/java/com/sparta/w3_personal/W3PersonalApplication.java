package com.sparta.w3_personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class W3PersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(W3PersonalApplication.class, args);
    }

}
