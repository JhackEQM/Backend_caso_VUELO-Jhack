package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AlquileresEntity;
import com.example.demo.repository.AlquileresRepository;
import com.example.demo.service.Operaciones;

@Service
public class AlquileresServiceImpl implements Operaciones<AlquileresEntity> {
	
	@Autowired
	private AlquileresRepository alquileresRepository;
	
	@Override
	public AlquileresEntity create(AlquileresEntity t) {
		// TODO Auto-generated method stub
		return alquileresRepository.save(t);
	}
	@Override
	public AlquileresEntity update(AlquileresEntity t) {
		// TODO Auto-generated method stub
		return alquileresRepository.save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		alquileresRepository.deleteById(id);
	}
	@Override
	public Optional<AlquileresEntity> read(Long id){
		// TODO Auto-generated method stub
		return alquileresRepository.findById(id);
	}
	@Override
	public List<AlquileresEntity> readAll(){
		// TODO Auto-generated method stub
		return alquileresRepository.findAll();
	}

}
