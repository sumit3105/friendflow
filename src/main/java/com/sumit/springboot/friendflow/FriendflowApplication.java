package com.sumit.springboot.friendflow;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendflowApplication {

    public static void main(String[] args) {
        // Load .env file and set environment variables
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

        SpringApplication.run(FriendflowApplication.class, args);
    }
}
