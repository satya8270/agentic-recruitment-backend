package com.platform.agentic_recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class AgenticRecruitmentApplication {

    public static void main(String[] args) {
        // Force Java to use the modern timezone name BEFORE the database connects
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        
        SpringApplication.run(AgenticRecruitmentApplication.class, args);
    }
}