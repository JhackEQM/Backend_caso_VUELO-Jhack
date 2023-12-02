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

import com.example.demo.entity.CategoriasEntity;
import com.example.demo.serviceImpl.CategoriaServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_CATEGORIAS;

@RestController
@RequestMapping(API_CATEGORIAS)
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriasController {
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoriasEntity>> listar(){
		try {
			List<CategoriasEntity> car = categoriaServiceImpl.readAll();
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
	public ResponseEntity<CategoriasEntity> getCategoriasEntityById(@PathVariable("ID_CATEGORIA") Long id){
		Optional<CategoriasEntity> carData = categoriaServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<CategoriasEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Crearcategorias")
	public ResponseEntity<CategoriasEntity> crear(@Valid @RequestBody CategoriasEntity categoriasEntity){
		try {
			CategoriasEntity _cat = categoriaServiceImpl.create(categoriasEntity);
			return new ResponseEntity<CategoriasEntity>(_cat, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_CATEGORIA}")
	public ResponseEntity<?> updateCategorias(@PathVariable("ID_CATEGORIA") Long id, @Valid @RequestBody CategoriasEntity categoriasEntity){
		Optional<CategoriasEntity> carData = categoriaServiceImpl.read(id);
			if (carData.isPresent()) {
				CategoriasEntity dbcategorias = carData.get();
				dbcategorias.setCategoria(categoriasEntity.getCategoria());
				return new ResponseEntity<CategoriasEntity>(categoriaServiceImpl.update(dbcategorias), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	@DeleteMapping("/delete/{ID_CATEGORIA}")
	public ResponseEntity<CategoriasEntity> delete(@PathVariable("ID_CATEGORIA") Long id){
		try {
			categoriaServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
