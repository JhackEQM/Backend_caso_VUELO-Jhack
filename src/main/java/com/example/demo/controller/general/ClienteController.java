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

//import com.example.demo.entity.ClientesEntity;
import com.example.demo.entity.ClienteEntity;
import com.example.demo.serviceImpl.ClienteServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_CLIENTE;

@RestController
@RequestMapping(API_CLIENTE)
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<ClienteEntity>> listar(){
		try {
			List<ClienteEntity> car = clienteServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 @GetMapping("/all")
	public List<?> listar(){
		try {
			List<?> car = clienteServiceImpl.readAllExample();
					//clienteServiceImpl.readAll();
			//List<ClienteEntity> car2;
			if (car.isEmpty()) {
				//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return car;
					//new ResponseEntity<>(car2, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
			//new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	  */
	@GetMapping("/BuscarID/{ID_Cliente}")
	public ResponseEntity<ClienteEntity> getClienteEntityById(@PathVariable("ID_Cliente") Long id){
		Optional<ClienteEntity> carData = clienteServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<ClienteEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/CrearCliente")
	public ResponseEntity<ClienteEntity> crear(@Valid @RequestBody ClienteEntity ClienteEntity){
		try {
			ClienteEntity _cat = clienteServiceImpl.create(ClienteEntity);
			return new ResponseEntity<ClienteEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_Cliente}")
	public ResponseEntity<?> updateCliente(@PathVariable("ID_Cliente") Long id, @Valid @RequestBody ClienteEntity ClienteEntity){
		Optional<ClienteEntity> carData = clienteServiceImpl.read(id);
			if (carData.isPresent()) {
				ClienteEntity dbCliente = carData.get();
				dbCliente.setClientedni(ClienteEntity.getClientedni());
				dbCliente.setClientenombre(ClienteEntity.getClientenombre());
				dbCliente.setClienteapellidos(ClienteEntity.getClienteapellidos());
				dbCliente.setClientetelefono(ClienteEntity.getClientetelefono());
				dbCliente.setClienteemail(ClienteEntity.getClienteemail());
				return new ResponseEntity<ClienteEntity>(clienteServiceImpl.update(dbCliente), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_Cliente}")
	public ResponseEntity<ClienteEntity> delete(@PathVariable("ID_Cliente") Long id){
		try {
			clienteServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
