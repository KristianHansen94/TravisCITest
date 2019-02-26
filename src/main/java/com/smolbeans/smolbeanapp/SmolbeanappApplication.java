package com.smolbeans.smolbeanapp;

import com.smolbeans.smolbeanapp.rest.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ↑ Dit is een false positive volgens stackOverflow
public class SmolbeanappApplication {

    @Autowired
    BeanService beanService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SmolbeanappApplication.class, args);
    }

    public SmolbeanappApplication() {
    }
}
