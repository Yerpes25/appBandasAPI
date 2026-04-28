package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "insignia")
public class Insignia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInsignia")
	private Integer idInsignia;

	@Column(name = "Nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", length = 1000)
	private String descripcion;

	@Column(name = "meta")
	private Integer meta;

	@Column(name = "icono", length = 255)
	private String icono;

	public Insignia() {
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
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