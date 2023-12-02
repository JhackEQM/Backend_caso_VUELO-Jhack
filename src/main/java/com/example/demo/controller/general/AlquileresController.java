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

import com.example.demo.entity.AlquileresEntity;
import com.example.demo.serviceImpl.AlquileresServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_ALQUILERES;

@RestController
@RequestMapping(API_ALQUILERES)
@CrossOrigin(origins = "http://localhost:4200")
public class AlquileresController {
	@Autowired
	private AlquileresServiceImpl alquileresServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<AlquileresEntity>> listar(){
		try {
			List<AlquileresEntity> car = alquileresServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/BuscarID/{ID_ALQUILERES}")
	public ResponseEntity<AlquileresEntity> getAlquileresEntityById(@PathVariable("ID_ALQUILERES") Long id){
		Optional<AlquileresEntity> carData = alquileresServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<AlquileresEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/Crearalquiler")
	public ResponseEntity<AlquileresEntity> crear(@Valid @RequestBody AlquileresEntity alquileresEntity){
		try {
			AlquileresEntity _alq = alquileresServiceImpl.create(alquileresEntity);
			return new ResponseEntity<AlquileresEntity>(_alq, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_ALQUILERES}")
	public ResponseEntity<?> updateAlquiler(@PathVariable("ID_ALQUILERES") Long id, @Valid @RequestBody AlquileresEntity alquileresEntity){
		Optional<AlquileresEntity> carData = alquileresServiceImpl.read(id);
			if (carData.isPresent()) {
				AlquileresEntity dbalquileres = carData.get();
				dbalquileres.setFecha_salida(alquileresEntity.getFecha_salida());
				dbalquileres.setFecha_entrada(alquileresEntity.getFecha_entrada());
				return new ResponseEntity<AlquileresEntity>(alquileresServiceImpl.update(dbalquileres), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_ALQUILERES}")
	public ResponseEntity<AlquileresEntity> delete(@PathVariable("ID_ALQUILERES") Long id){
		try {
			alquileresServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
