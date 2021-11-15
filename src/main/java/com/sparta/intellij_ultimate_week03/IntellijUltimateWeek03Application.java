package com.sparta.intellij_ultimate_week03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IntellijUltimateWeek03Application {

    public static void main(String[] args) {
        SpringApplication.run(IntellijUltimateWeek03Application.class, args);
    }

}
