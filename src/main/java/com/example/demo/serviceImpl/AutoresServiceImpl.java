package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AutoresEntity;
import com.example.demo.repository.AutoresRepository;
import com.example.demo.service.Operaciones;

@Service
public class AutoresServiceImpl implements Operaciones<AutoresEntity> {
	@Autowired
	private AutoresRepository autoresRepository;
	
	@Override
	public AutoresEntity create(AutoresEntity t) {
		// TODO Auto-generated method stub
		return autoresRepository.save(t);
	}
	@Override
	public AutoresEntity update(AutoresEntity t) {
		//TODO Auto-generated method stub
		return autoresRepository.save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		autoresRepository.deleteById(id);
	}
	@Override
	public Optional<AutoresEntity> read(Long id){
		// TODO Auto-generated method stub
		return autoresRepository.findById(id);
	}
	@Override
	public List<AutoresEntity> readAll(){
		// TODO Auto-generated method stub
		return autoresRepository.findAll();
	}
	public AutoresEntity searchAutoresEntity(String autor) {
		return autoresRepository.findAutoresEntityByAutorNative(autor);
	}
}
