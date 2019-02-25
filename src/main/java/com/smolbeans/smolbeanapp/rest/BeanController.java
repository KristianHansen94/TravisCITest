package com.smolbeans.smolbeanapp.rest;

import com.smolbeans.smolbeanapp.daos.BeanDao;
import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class BeanController {
    private final BeanDao repository;

    BeanController(BeanDao repository){
        this.repository = repository;
    }

    @GetMapping("/beans")
    List<Bean> getBeans(){
        return repository.findAll();
    }

    @PostMapping("/beans")
    Bean newBean(@RequestBody Bean newBean){
        return repository.persist(newBean);
    }

    @GetMapping("/beans/{id}")
    Bean getBean(@PathVariable("id") Long id){
        return repository.find(id.intValue());
    }

    @PutMapping("/beans/{id}")
    Bean replaceBean(@RequestBody Bean newBean, @PathVariable("id") Long id){
        return repository.persist(newBean);
//        return repository.findById(id).map(Bean -> {Bean.setName(newBean.getName());
//        Bean.setWeight(newBean.getWeight());
//        return repository.save(Bean);}).orElseGet(() -> {newBean.setId(id.intValue()); return repository.save(newBean);});
    }

    @DeleteMapping("/beans/{id}")
    void deleteBean(@PathVariable("id") Long id){
        repository.remove(id.intValue());
    }
}
