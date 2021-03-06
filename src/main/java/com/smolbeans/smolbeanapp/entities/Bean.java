package com.smolbeans.smolbeanapp.entities;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "beans")
public class Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int weight;

    public Bean() {
    }

    public Bean(String name, int weight) {
        this.setName(name);
        this.setWeight(weight);
    }

    // getters and setters for name and weight
    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", weight: " + weight;
    }
}
