package com.smolbeans.smolbeanapp.application;

import com.smolbeans.smolbeanapp.daos.BeanDao;
import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

//@SpringBootApplication
// ↑ Dit is een false positive volgens stackOverflow
public class SmolbeanappApplication {
    private final BeanDao beanDao;
    private final BufferedReader beanInputReader;


    public static void main(String[] args) throws Exception {
        //SpringApplication.run(SmolbeanappApplication.class, args);

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("user-unit")
                .createEntityManager();
        BeanDao beanDao = new BeanDao(entityManager);
        BufferedReader userInputReader =
                new BufferedReader(new InputStreamReader(System.in));
        new SmolbeanappApplication(beanDao, userInputReader).run();
    }

    public SmolbeanappApplication(BeanDao beanDao, BufferedReader beanInputReader) {
        this.beanDao = beanDao;
        this.beanInputReader = beanInputReader;
    }

    private void run() throws IOException {
        System.out.println("Enter an option: 1) Insert a new smolbean. 2) Find a smolbean. 3) Edit a smolbean. 4) Delete a smolbean. 5) Find all smolbeans.");
        int option = Integer.parseInt(beanInputReader.readLine());
        switch (option) {
            case 1:
                persistNewUser();
                run();
                break;
            case 2:
                fetchExistingUser();
                run();
                break;
            case 3:
                updateExistingUser();
                run();
                break;
            case 4:
                removeExistingUser();
                run();
                break;
            case 5:
                fetchAllUsers();
                run();
                break;
            default:
                System.out.println("Wrong command.");
                run();
        }
    }

    private void persistNewUser() throws IOException {
        String name = requestStringInput("the name of the smolbean");
        int weight = requestIntegerInput("the weight of the smolbean");
        beanDao.persist(new Bean(name, weight));
    }

    private void fetchExistingUser() throws IOException {
        int id = requestIntegerInput("the smolbean ID");
        Bean bean = beanDao.find(id);
        System.out.print("Name : " + bean.getName() + " Weight : " + bean.getWeight());
    }

    private void updateExistingUser() throws IOException {
        int id = requestIntegerInput("the smolbean ID");
        String name = requestStringInput("the name of the smolbean");
        int weight = requestIntegerInput("the weight of the smolbean");
        beanDao.update(id, name, weight);
    }

    private void removeExistingUser() throws IOException {
        int id = requestIntegerInput("the smolbean ID");
        beanDao.remove(id);
    }

    private String requestStringInput(String request) throws IOException {
        System.out.printf("Enter %s: ", request);
        return beanInputReader.readLine();
    }

    private int requestIntegerInput(String request) throws IOException {
        System.out.printf("Enter %s: ", request);
        return Integer.parseInt(beanInputReader.readLine());
    }

    private void fetchAllUsers() throws IOException{
        List<Bean> beans = beanDao.findAll();
        for (Bean b : beans) {
            System.out.println(b);
        }
    }
}
