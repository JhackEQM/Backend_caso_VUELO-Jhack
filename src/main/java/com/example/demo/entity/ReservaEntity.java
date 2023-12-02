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
@Table(name = "Reservas")
public class ReservaEntity {
	
	@Column(name = "ID_Reserva")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReserva")
	@SequenceGenerator(name = "seqReserva", allocationSize = 1, sequenceName = "SEQ_RESERVA")
	@Builder.Default
	private Long id = 0L;
	
	@Id 
	@Column(name = "Clase")
	private String reservaClase;
	
	@ManyToOne
	@JoinColumn(name = "ID_Cliente", nullable = false)
	private ClienteEntity clientes;
	
	@ManyToOne
	@JoinColumn(name = "ID_Hotel", nullable = false)
	private HotelEntity hoteles;
	
	@ManyToOne
	@JoinColumn(name = "ID_Sucursal", nullable = false)
	private SucursalEntity sucursales;
	
	@ManyToOne
	@JoinColumn(name = "ID_Vuelo", nullable = false)
	private VueloEntity vuelos;
	
	
	public ReservaEntity(String reservaClase, VueloEntity vuelos, ClienteEntity clientes, HotelEntity hoteles, SucursalEntity sucursales) {
		super();
		this.reservaClase = reservaClase;
		this.vuelos = vuelos;
		this.clientes = clientes;
		this.hoteles = hoteles;
		this.sucursales = sucursales;
	}

	public ReservaEntity(long id,String reservaClase, VueloEntity vuelos, ClienteEntity clientes, HotelEntity hoteles, SucursalEntity sucursales) {
		super();
		this.id = id;
		this.reservaClase = reservaClase;
		this.vuelos = vuelos;
		this.clientes = clientes;
		this.hoteles = hoteles;
		this.sucursales = sucursales;
	} 


}
