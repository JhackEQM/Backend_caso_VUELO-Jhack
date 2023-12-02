package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AutoresEntity;

@Repository
public interface AutoresRepository extends JpaRepository<AutoresEntity, Long>{
	
	@Query(value = "SELECT * FROM TBL_AUTORES u WHERE u.autor = ?1", nativeQuery = true)
	AutoresEntity findAutoresEntityByAutorNative(String autor);
}
