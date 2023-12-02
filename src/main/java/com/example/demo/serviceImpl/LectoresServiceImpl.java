package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LectoresEntity;
import com.example.demo.repository.LectoresRepository;
import com.example.demo.service.Operaciones;

@Service
public class LectoresServiceImpl implements Operaciones<LectoresEntity> {
	
	@Autowired
	private LectoresRepository lectoresRepository;
	
	@Override
	public LectoresEntity create(LectoresEntity t) {
		// TODO Auto-generated method stub
		return lectoresRepository.save(t);
	}
	@Override
	public LectoresEntity update(LectoresEntity t) {
		// TODO Auto-generated method stub
		return lectoresRepository.save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		lectoresRepository.deleteById(id);
	}
	@Override
	public Optional<LectoresEntity> read(Long id){
		// TODO Auto-generated method stub
		return lectoresRepository.findById(id);
	}
	@Override
	public List<LectoresEntity> readAll(){
		// TODO Auto-generated method stub
		return lectoresRepository.findAll();
	}
}
