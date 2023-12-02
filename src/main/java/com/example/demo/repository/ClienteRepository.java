package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
/*
@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
	@Query("SELECT p.nombre as name FROM Cliente p WHERE p.id = ?1")
	List<?> findExample(long id);
}
*/