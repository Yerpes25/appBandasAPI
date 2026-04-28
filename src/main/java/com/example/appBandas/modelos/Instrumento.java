package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "instrumento")
public class Instrumento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInstrumento")
	private Integer idInstrumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSeccion", nullable = false
)
	private Seccion seccion;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "esViento")
	private Boolean esViento = true;

	// Constructores
	public Instrumento() {
	}

	// Getters y Setters
	public Integer getIdInstrumento() {
		return idInstrumento;
	}

	public void setIdInstrumento(Integer idInstrumento) {
		this.idInstrumento = idInstrumento;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEsViento() {
		return esViento;
	}

	public void setEsViento(Boolean esViento) {
		this.esViento = esViento;
	}
}