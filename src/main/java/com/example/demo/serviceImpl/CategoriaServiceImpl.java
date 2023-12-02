package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CategoriasEntity;
import com.example.demo.repository.CategoriasRepository;
import com.example.demo.service.Operaciones;

@Service
public class CategoriaServiceImpl implements Operaciones<CategoriasEntity> {
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@Override
	public CategoriasEntity create(CategoriasEntity t) {
		// TODO Auto-generated method stub
		return categoriasRepository.save(t);
	}
	@Override
	public CategoriasEntity update(CategoriasEntity t) {
		//TODO Auto-generated method stub
		return categoriasRepository.save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categoriasRepository.deleteById(id);
	}
	@Override
	public Optional<CategoriasEntity> read(Long id){
		// TODO Auto-generated method stub
		return categoriasRepository.findById(id);
	}
	@Override
	public List<CategoriasEntity> readAll(){
		// TODO Auto-generated method stub
		return categoriasRepository.findAll();
	}
}
