package com.example.thecoffeehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThecoffeehouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThecoffeehouseApplication.class, args);
        System.out.println("=== The Coffee House Website is running ===");
    }
}
