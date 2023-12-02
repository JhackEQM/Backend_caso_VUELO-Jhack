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

import com.example.demo.entity.HotelEntity;
import com.example.demo.serviceImpl.HotelServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_HOTEL;

@RestController
@RequestMapping(API_HOTEL)
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<HotelEntity>> listar(){
		try {
			List<HotelEntity> car = hotelServiceImpl.readAll();
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
	public ResponseEntity<HotelEntity> getHotelEntityById(@PathVariable("ID_CATEGORIA") Long id){
		Optional<HotelEntity> carData = hotelServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<HotelEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/CrearHotel")
	public ResponseEntity<HotelEntity> crear(@Valid @RequestBody HotelEntity HotelEntity){
		try {
			HotelEntity _cat = hotelServiceImpl.create(HotelEntity);
			return new ResponseEntity<HotelEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_CATEGORIA}")
	public ResponseEntity<?> updateHotel(@PathVariable("ID_CATEGORIA") Long id, @Valid @RequestBody HotelEntity HotelEntity){
		Optional<HotelEntity> carData = hotelServiceImpl.read(id);
			if (carData.isPresent()) {
				HotelEntity dbHotel = carData.get();
				dbHotel.setHotelNombre(HotelEntity.getHotelNombre());
				dbHotel.setHotelDireccion(HotelEntity.getHotelDireccion());
				dbHotel.setHoteLocaldadl(HotelEntity.getHoteLocaldadl());
				dbHotel.setHotelProvincia(HotelEntity.getHotelProvincia());
				dbHotel.setHotelTelefono(HotelEntity.getHotelTelefono());
				dbHotel.setHotelEstrellas(HotelEntity.getHotelEstrellas());

				return new ResponseEntity<HotelEntity>(hotelServiceImpl.update(dbHotel), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_CATEGORIA}")
	public ResponseEntity<HotelEntity> delete(@PathVariable("ID_CATEGORIA") Long id){
		try {
			hotelServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
