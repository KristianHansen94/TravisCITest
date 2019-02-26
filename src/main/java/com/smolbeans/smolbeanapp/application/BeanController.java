package com.smolbeans.smolbeanapp.application;

import com.smolbeans.smolbeanapp.entities.Bean;
import com.smolbeans.smolbeanapp.rest.BeanRepository;
import com.smolbeans.smolbeanapp.rest.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BeanController implements BeanService {
    @Autowired
    private BeanRepository repository;


    @GetMapping("/beans")
    public List<Bean> getBeans(){
        return repository.findAll();
    }

    @PostMapping("/beans")
    public Bean newBean(@RequestBody Bean newBean){
        return repository.save(newBean);
    }

    @GetMapping("/beans/{id}")
    public Bean getBean(@PathVariable("id") Long id){
        Bean gottenBean = repository.findById(id).get();
        return gottenBean;
    }

    @PutMapping("/beans/{id}")
    public Bean replaceBean(@RequestBody Bean newBean, @PathVariable("id") Long id){
        return repository.findById(id).map(Bean -> {Bean.setName(newBean.getName());
        Bean.setWeight(newBean.getWeight());
        return repository.save(Bean);}).orElseGet(() -> {newBean.setId(id); return repository.save(newBean);});
    }

    @DeleteMapping("/beans/{id}")
    public void deleteBean(@PathVariable("id") Long id){
        repository.deleteById(id);
    }
}
