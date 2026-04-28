package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "voz")
public class Voz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVoz")
	private Integer idVoz;

	@Column(name = "Nombre", nullable = false, length = 50)
	private String nombre;

	// Constructores
	public Voz() {
	}

	// Getters y Setters
	public Integer getIdVoz() {
		return idVoz;
	}

	public void setIdVoz(Integer idVoz) {
		this.idVoz = idVoz;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}