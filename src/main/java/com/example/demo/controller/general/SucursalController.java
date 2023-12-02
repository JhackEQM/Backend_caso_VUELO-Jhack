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

import com.example.demo.entity.SucursalEntity;
import com.example.demo.serviceImpl.SucursalServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_SUCURSAL;

@RestController
@RequestMapping(API_SUCURSAL)
@CrossOrigin(origins = "http://localhost:4200")
public class SucursalController {
	@Autowired
	private SucursalServiceImpl SucursalServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<SucursalEntity>> listar(){
		try {
			List<SucursalEntity> car = SucursalServiceImpl.readAll();
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
	public ResponseEntity<SucursalEntity> getSucursalEntityById(@PathVariable("ID_CATEGORIA") Long id){
		Optional<SucursalEntity> carData = SucursalServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<SucursalEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/CrearSucursal")
	public ResponseEntity<SucursalEntity> crear(@Valid @RequestBody SucursalEntity SucursalEntity){
		try {
			SucursalEntity _cat = SucursalServiceImpl.create(SucursalEntity);
			return new ResponseEntity<SucursalEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_CATEGORIA}")
	public ResponseEntity<?> updateSucursal(@PathVariable("ID_CATEGORIA") Long id, @Valid @RequestBody SucursalEntity SucursalEntity){
		Optional<SucursalEntity> carData = SucursalServiceImpl.read(id);
			if (carData.isPresent()) {
				SucursalEntity dbSucursal = carData.get();
				dbSucursal.setSucursalDireccion(SucursalEntity.getSucursalDireccion());
				dbSucursal.setSucursalLocalidad(SucursalEntity.getSucursalLocalidad());
				dbSucursal.setSucursalProvincia(SucursalEntity.getSucursalProvincia());
				dbSucursal.setSucursalTelefono(SucursalEntity.getSucursalTelefono());
				return new ResponseEntity<SucursalEntity>(SucursalServiceImpl.update(dbSucursal), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_CATEGORIA}")
	public ResponseEntity<SucursalEntity> delete(@PathVariable("ID_CATEGORIA") Long id){
		try {
			SucursalServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
