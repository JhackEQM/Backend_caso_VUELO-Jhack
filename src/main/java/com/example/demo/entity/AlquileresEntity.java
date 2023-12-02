package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "TBL_ALQUILERES")
public class AlquileresEntity {
	@Id 
	@Column(name = "ID_ALQUILERES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLibrosLectores")
	@SequenceGenerator(name = "seqLibrosLectores", allocationSize = 1, sequenceName = "SEQ_ALQUILERES")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "FECHA_SALIDA")
	private String fecha_salida;
	
	@Column(name = "FECHA_ENTRADA")
	private String fecha_entrada;
	 
	/* RELACIONES */
	
	@ManyToOne
	@JoinColumn(name = "ID_LIBRO", nullable = false)
	private LibrosEntity libros;
	
	@ManyToOne
	@JoinColumn(name = "ID_LECTOR", nullable = false)
	private LectoresEntity lectores;
}
