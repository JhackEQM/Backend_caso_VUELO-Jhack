package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LibrosEntity;
import com.example.demo.repository.LibrosRepository;
import com.example.demo.service.Operaciones;

@Service
public class LibrosServiceImpl implements Operaciones<LibrosEntity> {
	@Autowired
	private LibrosRepository librosRepository;
	
	@Override
	public LibrosEntity create (LibrosEntity t) {
		// TODO Auto-generated method stub
		return librosRepository.save(t);
	}
	@Override
	public LibrosEntity update (LibrosEntity t) {
		// TODO Auto-generated method stub
		return librosRepository.save(t);
	}
	@Override
	public void delete (Long id) {
		// TODO Auto-generated method stub
		librosRepository.deleteById(id);
	}
	@Override
	public Optional<LibrosEntity> read(Long id){
		// TODO Auto-generated method stub
		return librosRepository.findById(id);
	}
	@Override 
	public List<LibrosEntity> readAll(){
		// TODO Auto-generated method stub
		return librosRepository.findAll();
	}

}
