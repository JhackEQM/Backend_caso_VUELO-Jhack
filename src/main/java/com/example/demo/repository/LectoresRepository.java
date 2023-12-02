package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LectoresEntity;

@Repository
public interface LectoresRepository extends JpaRepository<LectoresEntity, Long> {

}
