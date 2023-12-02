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
@Table(name = "Sucursales")
public class SucursalEntity {
	@Id 
	@Column(name = "ID_Sucursal")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSucursal")
	@SequenceGenerator(name = "seqSucursal", allocationSize = 1, sequenceName = "SEQ_SUCURSAL")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "Dirección")
	private String sucursalDireccion;
	
	@Column(name = "Localidad")
	private String sucursalLocalidad;
	
	@Column(name = "Provincia")
	private String sucursalProvincia;
	
	@Column(name = "Telefono")
	private String sucursalTelefono;
	 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sucursales")
	@JsonIgnore
	private Set<ReservaEntity> reservas;


}
