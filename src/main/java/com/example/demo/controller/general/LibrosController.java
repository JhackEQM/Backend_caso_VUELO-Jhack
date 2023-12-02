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
import com.example.demo.entity.CategoriasEntity;
import com.example.demo.entity.EditorialesEntity;
import com.example.demo.entity.LibroDto;
import com.example.demo.entity.LibrosEntity;
import com.example.demo.serviceImpl.LibrosServiceImpl;

import jakarta.validation.Valid;

import static com.example.demo.commons.GlobalConstants.API_LIBROS;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(API_LIBROS)
public class LibrosController {
	@Autowired
	private LibrosServiceImpl librosServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<LibrosEntity>> listar(){
		try {
			List<LibrosEntity> car = librosServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/BuscarID/{ID_LIBRO}")
	public ResponseEntity<LibrosEntity> getLibrosEntityById(@PathVariable("ID_LIBRO") Long id){
		Optional<LibrosEntity> carData = librosServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<LibrosEntity>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Crearlibros")
	public ResponseEntity<LibrosEntity> crear(@RequestBody LibroDto librosEntity){
		try {
			AutoresEntity autores = new AutoresEntity(librosEntity.getAutores(), "", null);
			CategoriasEntity categoria = new CategoriasEntity(librosEntity.getCategorias(), "", null);
			EditorialesEntity editoriales = new EditorialesEntity(librosEntity.getEditoriales(), "", null);
			LibrosEntity libro = new LibrosEntity(librosEntity.getTitulo(), librosEntity.getFecha_de_lanzamiento(), librosEntity.getIdioma(), librosEntity.getPaginas(), librosEntity.getDescripcion(), librosEntity.getPortada(), autores, categoria, editoriales);
			LibrosEntity _lect = librosServiceImpl.create(libro);
			return new ResponseEntity<LibrosEntity>(_lect, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{ID_LIBRO}")
	public ResponseEntity<?> updateLibros(@PathVariable("ID_LIBRO") Long id, @Valid @RequestBody LibroDto librosEntity){
		try {
			AutoresEntity autores = new AutoresEntity(librosEntity.getAutores(), "", null);
			CategoriasEntity categoria = new CategoriasEntity(librosEntity.getCategorias(), "", null);
			EditorialesEntity editoriales = new EditorialesEntity(librosEntity.getEditoriales(), "", null);
			LibrosEntity libro = new LibrosEntity(id,librosEntity.getTitulo(), librosEntity.getFecha_de_lanzamiento(), librosEntity.getIdioma(), librosEntity.getPaginas(), librosEntity.getDescripcion(), librosEntity.getPortada(), autores, categoria, editoriales);
			LibrosEntity _lect = librosServiceImpl.create(libro);
			return new ResponseEntity<LibrosEntity>(_lect, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delete/{ID_LIBRO}")
	public ResponseEntity<LibrosEntity> delete(@PathVariable("ID_LIBRO") Long id){
		try {
			librosServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
