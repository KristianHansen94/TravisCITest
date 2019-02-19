package com.smolbeans.smolbeanapp.application;

import com.smolbeans.smolbeanapp.daos.BeanDao;
import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class SmolbeanappApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SmolbeanappApplication.class, args);

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("user-unit")
                .createEntityManager();
        BeanDao beanDao = new BeanDao(entityManager);
        BufferedReader userInputReader =
                new BufferedReader(new InputStreamReader(System.in));
        new SmolbeanappApplication(beanDao, userInputReader).run();
    }
}
