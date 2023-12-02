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

import com.example.demo.entity.EditorialesEntity;
import com.example.demo.serviceImpl.EditorialesServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_EDITORIALES;

@RestController
@RequestMapping(API_EDITORIALES)
@CrossOrigin(origins = "http://localhost:4200")
public class EditorialesController {
	@Autowired
	private EditorialesServiceImpl editorialesServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<EditorialesEntity>> listar(){
		try {
			List<EditorialesEntity> car = editorialesServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/BuscarID/{ID_EDITORIAL}")
	public ResponseEntity<EditorialesEntity> getEditorialesEntityById(@PathVariable("ID_EDITORIAL") Long id){
		Optional<EditorialesEntity> carData = editorialesServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<EditorialesEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Creareditoriales")
	public ResponseEntity<EditorialesEntity> crear(@Valid @RequestBody EditorialesEntity editorialesEntity){
		try {
			EditorialesEntity _edit = editorialesServiceImpl.create(editorialesEntity);
			return new ResponseEntity<EditorialesEntity>(_edit, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_EDITORIAL}")
	public ResponseEntity<?> updateEditoriales(@PathVariable("ID_EDITORIAL") Long id, @Valid @RequestBody EditorialesEntity editorialesEntity){
		Optional<EditorialesEntity> carData = editorialesServiceImpl.read(id);
			if (carData.isPresent()) {
				EditorialesEntity dbeditoriales = carData.get();
				dbeditoriales.setEditorial(editorialesEntity.getEditorial());
				return new ResponseEntity<EditorialesEntity>(editorialesServiceImpl.update(dbeditoriales), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_EDITORIAL}")
	public ResponseEntity<EditorialesEntity> delete(@PathVariable("ID_EDITORIAL") Long id){
		try {
			editorialesServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
