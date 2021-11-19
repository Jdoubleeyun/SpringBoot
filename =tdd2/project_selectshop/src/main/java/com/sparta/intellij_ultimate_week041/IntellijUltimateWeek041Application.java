package com.sparta.intellij_ultimate_week041;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class IntellijUltimateWeek041Application {

    public static void main(String[] args) {
        SpringApplication.run(IntellijUltimateWeek041Application.class, args);
    }

}

