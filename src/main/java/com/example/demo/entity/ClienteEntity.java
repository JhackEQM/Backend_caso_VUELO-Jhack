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
@Table(name = "Clientes")
public class ClienteEntity {
	@Id 
	@Column(name = "ID_Cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
	@SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_CLIENTE")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name = "DNI")
	private String clientedni;
	
	@Column(name = "NOMBRE")
	private String clientenombre;
	
	@Column(name = "Apellidos")
	private String clienteapellidos;
	
	@Column(name = "Telefono")
	private String clientetelefono;
	
	@Column(name = "Email")
	private String clienteemail;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientes")
	@JsonIgnore
	private Set<ReservaEntity> reservas;
	 


}
