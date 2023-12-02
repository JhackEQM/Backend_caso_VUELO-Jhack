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

import com.example.demo.entity.VueloEntity;
import com.example.demo.serviceImpl.VueloServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_VUELO;

@RestController
@RequestMapping(API_VUELO)
@CrossOrigin(origins = "http://localhost:4200")
public class VueloController {
	@Autowired
	private VueloServiceImpl VueloServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<VueloEntity>> listar(){
		try {
			List<VueloEntity> car = VueloServiceImpl.readAll();
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
	public ResponseEntity<VueloEntity> getVueloEntityById(@PathVariable("ID_CATEGORIA") Long id){
		Optional<VueloEntity> carData = VueloServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<VueloEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/CrearVuelo")
	public ResponseEntity<VueloEntity> crear(@Valid @RequestBody VueloEntity VueloEntity){
		try {
			VueloEntity _cat = VueloServiceImpl.create(VueloEntity);
			return new ResponseEntity<VueloEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_CATEGORIA}")
	public ResponseEntity<?> updateVuelo(@PathVariable("ID_CATEGORIA") Long id, @Valid @RequestBody VueloEntity VueloEntity){
		Optional<VueloEntity> carData = VueloServiceImpl.read(id);
			if (carData.isPresent()) {
				VueloEntity dbVuelo = carData.get();
				dbVuelo.setVueloFSalida(VueloEntity.getVueloFSalida());
				dbVuelo.setVueloHSalida(VueloEntity.getVueloHSalida());
				dbVuelo.setVueloFLlegada(VueloEntity.getVueloFLlegada());
				dbVuelo.setVueloHLlegada(VueloEntity.getVueloHLlegada());
				dbVuelo.setVueloOrigen(VueloEntity.getVueloOrigen());
				dbVuelo.setVueloDestino(VueloEntity.getVueloDestino());
				dbVuelo.setVueloNPTotales(VueloEntity.getVueloNPTotales());
				return new ResponseEntity<VueloEntity>(VueloServiceImpl.update(dbVuelo), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_CATEGORIA}")
	public ResponseEntity<VueloEntity> delete(@PathVariable("ID_CATEGORIA") Long id){
		try {
			VueloServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
