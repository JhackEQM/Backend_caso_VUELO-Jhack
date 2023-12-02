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
@Table(name = "TBL_LECTORES")
public class LectoresEntity {
	@Id
	@Column(name = "ID_LECTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLectores")
	@SequenceGenerator(name = "seqLectores", allocationSize = 1, sequenceName = "SEQ_LECTOREES")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "NOMBRE")
	@NotNull @NotBlank
	private String nombre;
	
	@Column(name = "TELEFONO")
	@NotNull 
	private Integer telefono;
	
	@Column(name = "DIRECCION")
	@NotNull @NotBlank
	private String direccion;
	
	@Column(name = "CODIGO_POSTAL")
	@NotNull 
	private Integer codigo_postal;
	
	@Column(name = "OBSERVACIONES")
	@NotNull @NotBlank
	private String observaciones;
	
	/* RELACIONES */
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lectores")
	@JsonIgnore
	private Set<AlquileresEntity> alquiler;
	

}
