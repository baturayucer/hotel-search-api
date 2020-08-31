package com.baturayucer.hotelsearch.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for Spring Boot Application.
 * @author baturayucer.
 */
@SpringBootApplication(scanBasePackages = "com.baturayucer")
public class HotelSearchApi {
    public static void main(String[] args) {
        SpringApplication.run(HotelSearchApi.class, args);
    }
}