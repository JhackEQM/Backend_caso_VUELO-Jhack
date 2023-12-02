package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EditorialesEntity;
import com.example.demo.repository.EditorialesRepository;
import com.example.demo.service.Operaciones;

@Service
public class EditorialesServiceImpl implements Operaciones<EditorialesEntity>{
	@Autowired
	private EditorialesRepository editorialesRepository;
	
	@Override
	public EditorialesEntity create(EditorialesEntity t) {
		// TODO Auto-generated method stub
		return editorialesRepository.save(t);
	}
	@Override
	public EditorialesEntity update(EditorialesEntity t) {
		//TODO Auto-generated method stub
		return editorialesRepository.save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		editorialesRepository.deleteById(id);
	}
	@Override
	public Optional<EditorialesEntity> read(Long id){
		// TODO Auto-generated method stub
		return editorialesRepository.findById(id);
	}
	@Override
	public List<EditorialesEntity> readAll(){
		// TODO Auto-generated method stub
		return editorialesRepository.findAll();
	}
}
