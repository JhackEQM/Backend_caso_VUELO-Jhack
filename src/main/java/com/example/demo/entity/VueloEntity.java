package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "Vuelos")
public class VueloEntity {
	@Id 
	@Column(name = "ID_Vuelo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVuelo")
	@SequenceGenerator(name = "seqVuelo", allocationSize = 1, sequenceName = "SEQ_VUELO")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "Fecha_Salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
	private String vueloFSalida;
	
	@Column(name = "Hora_Salida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
	private String vueloHSalida;
	
	@Column(name = "Fecha_Llegada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
	private String vueloFLlegada;
	
	@Column(name = "Hora_Llegada")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
	private String vueloHLlegada;
	
	@Column(name = "Origen")
	private String vueloOrigen;
	
	@Column(name = "Destino")
	private String vueloDestino;
	
	@Column(name = "Numero_Plazas_Totales")
	private int vueloNPTotales; 

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vuelos")
	@JsonIgnore
	private Set<ReservaEntity> reservas;

}
