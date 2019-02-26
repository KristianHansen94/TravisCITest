package com.smolbeans.smolbeanapp.rest;

import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BeanService {
    List<Bean> getBeans();
    Bean newBean(@RequestBody Bean newBean);
    Bean getBean(@PathVariable("id") Long id);
    Bean replaceBean(@RequestBody Bean newBean, @PathVariable("id") Long id);
    void deleteBean(@PathVariable("id") Long id);
}
