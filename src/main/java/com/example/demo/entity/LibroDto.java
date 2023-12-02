package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
public class LibroDto {
	private Long id;
	private String titulo;
	private String fecha_de_lanzamiento;
	private String idioma;
	private int paginas;	
	private String descripcion;
	private String portada;
	private Long autores;	
	private Long editoriales;
	private Long categorias;
	
	public LibroDto(String titulo, String fecha_de_lanzamiento, String idioma, int paginas, String descripcion,
			String portada, Long autores, Long editoriales, Long categorias) {
		super();
		this.titulo = titulo;
		this.fecha_de_lanzamiento = fecha_de_lanzamiento;
		this.idioma = idioma;
		this.paginas = paginas;
		this.descripcion = descripcion;
		this.portada = portada;
		this.autores = autores;
		this.editoriales = editoriales;
		this.categorias = categorias;
	}

	
}
