package com.smolbeans.smolbeanapp.rest;

import com.smolbeans.smolbeanapp.entities.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanRepository extends JpaRepository<Bean, Long> {
}
