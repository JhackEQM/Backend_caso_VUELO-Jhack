package com.example.demo.controller.general;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AutoresEntity;
import com.example.demo.serviceImpl.AutoresServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_AUTORES;

@RestController
@RequestMapping(API_AUTORES)
@CrossOrigin(origins = "http://localhost:4200")
public class AutoresController {
	@Autowired
	private AutoresServiceImpl autoresServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<AutoresEntity>> listar(){
		try {
			List<AutoresEntity> car = autoresServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/BuscarN/{AUTOR}")
	public ResponseEntity<AutoresEntity> getAutorEntityByAutor(@PathVariable("AUTOR")String autor){
		AutoresEntity autoresEntity = autoresServiceImpl.searchAutoresEntity(autor);
		if (autoresEntity!=null) {
			return new ResponseEntity<AutoresEntity>(autoresEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/BuscarID/{ID_AUTOR}")
	public ResponseEntity<AutoresEntity> getAutorEntityById(@PathVariable("ID_AUTOR") Long id){
		Optional<AutoresEntity> carData = autoresServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<AutoresEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Crearautor")
	public ResponseEntity<AutoresEntity> crear(@Valid @RequestBody AutoresEntity autoresEntity){
		try {
			AutoresEntity _aut = autoresServiceImpl.create(autoresEntity);
			return new ResponseEntity<AutoresEntity>(_aut, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_AUTOR}")
	public ResponseEntity<?> updateAutor(@PathVariable("ID_AUTOR") Long id, @Valid @RequestBody AutoresEntity autoresEntity){
		Optional<AutoresEntity> carData = autoresServiceImpl.read(id);
			if (carData.isPresent()) {
				AutoresEntity dbautores = carData.get();
				dbautores.setAutor(autoresEntity.getAutor());
				return new ResponseEntity<AutoresEntity>(autoresServiceImpl.update(dbautores), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_AUTOR}")
	public ResponseEntity<AutoresEntity> delete(@PathVariable("ID_AUTOR") Long id){
		try {
			autoresServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
