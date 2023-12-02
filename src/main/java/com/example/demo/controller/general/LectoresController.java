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

import com.example.demo.entity.LectoresEntity;
import com.example.demo.serviceImpl.LectoresServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_LECTORES;

@RestController
@RequestMapping(API_LECTORES)
@CrossOrigin(origins = "http://localhost:4200")
public class LectoresController {
	@Autowired
	private LectoresServiceImpl lectoresServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<LectoresEntity>> listar(){
		try {
			List<LectoresEntity> car = lectoresServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/BuscarID/{ID_LECTOR}")
	public ResponseEntity<LectoresEntity> getLectoresEntityById(@PathVariable("ID_LECTOR") Long id){
		Optional<LectoresEntity> carData = lectoresServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<LectoresEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Crearlectores")
	public ResponseEntity<LectoresEntity> crear(@Valid @RequestBody LectoresEntity lectoresEntity){
		try {
			LectoresEntity _lect = lectoresServiceImpl.create(lectoresEntity);
			return new ResponseEntity<LectoresEntity>(_lect, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_LECTOR}")
	public ResponseEntity<?> updateLectores(@PathVariable("ID_LECTOR") Long id, @Valid @RequestBody LectoresEntity lectoresEntity){
		Optional<LectoresEntity> carData = lectoresServiceImpl.read(id);
			if (carData.isPresent()) {
				LectoresEntity dblectores = carData.get();
				dblectores.setNombre(lectoresEntity.getNombre());
				dblectores.setTelefono(lectoresEntity.getTelefono());
				dblectores.setDireccion(lectoresEntity.getDireccion());
				dblectores.setCodigo_postal(lectoresEntity.getCodigo_postal());
				dblectores.setObservaciones(lectoresEntity.getObservaciones());
				return new ResponseEntity<LectoresEntity>(lectoresServiceImpl.update(dblectores), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_LECTOR}")
	public ResponseEntity<LectoresEntity> delete(@PathVariable("ID_LECTOR") Long id){
		try {
			lectoresServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
