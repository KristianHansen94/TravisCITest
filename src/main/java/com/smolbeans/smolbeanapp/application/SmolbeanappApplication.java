package com.smolbeans.smolbeanapp.application;

import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class SmolbeanappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmolbeanappApplication.class, args);
    }
}
