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

import com.example.demo.entity.ReservaEntity;
import com.example.demo.serviceImpl.ReservaServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_RESERVA;

@RestController
@RequestMapping(API_RESERVA)
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<ReservaEntity>> listar(){
		try {
			List<ReservaEntity> car = reservaServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/BuscarID/{ID_CATEGORIA}")
	public ResponseEntity<ReservaEntity> getReservaEntityById(@PathVariable("ID_CATEGORIA") Long id){
		Optional<ReservaEntity> carData = reservaServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<ReservaEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/CrearReserva")
	public ResponseEntity<ReservaEntity> crear(@Valid @RequestBody ReservaEntity ReservaEntity){
		try {
			ReservaEntity _cat = reservaServiceImpl.create(ReservaEntity);
			return new ResponseEntity<ReservaEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_CATEGORIA}")
	public ResponseEntity<?> updateReserva(@PathVariable("ID_CATEGORIA") Long id, @Valid @RequestBody ReservaEntity ReservaEntity){
		Optional<ReservaEntity> carData = reservaServiceImpl.read(id);
			if (carData.isPresent()) {
				ReservaEntity dbReserva = carData.get();
				dbReserva.setReservaClase(ReservaEntity.getReservaClase());
				return new ResponseEntity<ReservaEntity>(reservaServiceImpl.update(dbReserva), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_CATEGORIA}")
	public ResponseEntity<ReservaEntity> delete(@PathVariable("ID_CATEGORIA") Long id){
		try {
			reservaServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
