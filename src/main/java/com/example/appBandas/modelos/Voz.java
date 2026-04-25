package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que define las posibles voces o partituras que puede tener un
 * instrumento. Por ejemplo: 1º Alta, 1º Fuerte, 2º, Única.
 */
@Entity
@Table(name = "Voces")
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