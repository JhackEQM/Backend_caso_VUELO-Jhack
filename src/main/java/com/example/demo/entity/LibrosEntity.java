package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "TBL_LIBROS")
public class LibrosEntity {
	@Id
	@Column(name = "ID_LIBRO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLibros")
	@SequenceGenerator(name = "seqLibros", allocationSize = 1, sequenceName = "SEQ_LIBROOS")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "TITULO")
	@NotNull @NotBlank
	private String titulo;
	
	@Column(name = "FECHA_DE_LANZAMIENTO")
	@NotNull @NotBlank
	private String fecha_de_lanzamiento;
	
	@Column(name = "IDIOMA")
	@NotNull @NotBlank
	private String idioma;
	
	@Column(name = "PAGINAS")
	@NotNull 
	private Integer paginas;
	
	@Column(name = "DESCRIPCION")
	@NotNull @NotBlank
	private String descripcion;
	
	@Column(name = "PORTADA")
	@NotNull @NotBlank
	private String portada;
	
	/* RELACIONES */
	
	@ManyToOne
	@JoinColumn(name = "ID_AUTOR", nullable = false)
	private AutoresEntity autores;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	private CategoriasEntity categorias;
	
	@ManyToOne
	@JoinColumn(name = "ID_EDITORIAL", nullable = false)
	private EditorialesEntity editoriales;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libros")
	@JsonIgnore
	private Set<AlquileresEntity> alquiler;

	public LibrosEntity(@NotNull @NotBlank String titulo, @NotNull @NotBlank String fecha_de_lanzamiento,
			@NotNull @NotBlank String idioma, @NotNull Integer paginas, @NotNull @NotBlank String descripcion,
			@NotNull @NotBlank String portada, AutoresEntity autores, CategoriasEntity categorias,
			EditorialesEntity editoriales) {
		super();
		this.titulo = titulo;
		this.fecha_de_lanzamiento = fecha_de_lanzamiento;
		this.idioma = idioma;
		this.paginas = paginas;
		this.descripcion = descripcion;
		this.portada = portada;
		this.autores = autores;
		this.categorias = categorias;
		this.editoriales = editoriales;
	}

	public LibrosEntity(Long id, @NotNull @NotBlank String titulo, @NotNull @NotBlank String fecha_de_lanzamiento,
			@NotNull @NotBlank String idioma, @NotNull Integer paginas, @NotNull @NotBlank String descripcion,
			@NotNull @NotBlank String portada, AutoresEntity autores, CategoriasEntity categorias,
			EditorialesEntity editoriales) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.fecha_de_lanzamiento = fecha_de_lanzamiento;
		this.idioma = idioma;
		this.paginas = paginas;
		this.descripcion = descripcion;
		this.portada = portada;
		this.autores = autores;
		this.categorias = categorias;
		this.editoriales = editoriales;
	}
	
	
}
