package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que representa los logros o insignias que los usuarios pueden ganar.
 * Define el nombre, la descripción y la meta necesaria para obtenerla.
 */
@Entity
@Table(name = "Insignias")
public class Insignia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInsignia")
	private Integer idInsignia;

	@Column(name = "Nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "meta")
	private Integer meta;

	public Insignia() {
	}

	public Integer getIdInsignia() {
		return idInsignia;
	}

	public void setIdInsignia(Integer idInsignia) {
		this.idInsignia = idInsignia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getMeta() {
		return meta;
	}

	public void setMeta(Integer meta) {
		this.meta = meta;
	}
}