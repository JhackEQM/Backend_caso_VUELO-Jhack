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
@Table(name = "Hoteles")
public class HotelEntity {
	@Id 
	@Column(name = "ID_Hotel")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHotel")
	@SequenceGenerator(name = "seqHotel", allocationSize = 1, sequenceName = "SEQ_HOTEL")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "NOMBRE")
	private String hotelNombre;
	
	@Column(name = "Direccion")
	private String hotelDireccion;
	
	@Column(name = "Localdad")
	private String hoteLocaldadl;
	
	@Column(name = "Provincia")
	private String hotelProvincia;
	
	@Column(name = "Telefono")
	private String hotelTelefono;
	
	@Column(name = "N. ESTRELLAS")
	private int hotelEstrellas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hoteles")
	@JsonIgnore
	private Set<ReservaEntity> reservas;
	 


}
