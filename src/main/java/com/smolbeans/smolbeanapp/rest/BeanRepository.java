package com.smolbeans.smolbeanapp.rest;

import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BeanRepository extends JpaRepository<Bean, Long> {
}
