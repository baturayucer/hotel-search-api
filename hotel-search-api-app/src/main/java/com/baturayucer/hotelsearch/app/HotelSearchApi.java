package com.baturayucer.hotelsearch.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.baturayucer")
public class HotelSearchApi {
    public static void main(String[] args) {
        SpringApplication.run(HotelSearchApi.class, args);
    }
}