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
public class ReservaDto {
	
	private Long id;
	private String reservaClase;
	private Long clientes;	
	private Long hoteles;
	private Long sucursales;
	private Long vuelos;

	
	public ReservaDto(String reservaClase, Long clientes, Long hoteles, Long sucursales,Long vuelos) {
		super();
		this.reservaClase = reservaClase;
		this.clientes = clientes;
		this.hoteles = hoteles;
		this.sucursales = sucursales;
		this.vuelos = vuelos;
	}

	
}
