package com.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FetcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(FetcherApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://localhost:8443/api/data";
            try {
                System.out.println("--- Sending HTTPS Request to Vault ---");
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("Decrypted Response: " + response);
            } catch (Exception e) {
                System.err.println("Request failed: " + e.getMessage());
                System.err.println("Note: You must trust the self-signed cert or disable validation.");
            }
        };
    }
}
