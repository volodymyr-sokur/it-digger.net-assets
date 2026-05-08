package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class VaultApplication {
    public static void main(String[] args) {
        SpringApplication.run(VaultApplication.class, args);
    }
}

@RestController
class SecretController {
    @GetMapping("/api/data")
    public Map<String, String> getSecretData() {
        return Collections.singletonMap("payload", "CONFIDENTIAL: jSSLKeyLog is working!");
    }
}
