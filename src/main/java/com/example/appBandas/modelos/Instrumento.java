package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que detalla los tipos de instrumentos disponibles en el sistema. Cada
 * instrumento pertenece a una sección específica.
 */
@Entity
@Table(name = "Instrumentos")
public class Instrumento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInstrumento")
	private Integer idInstrumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSeccion", nullable = false)
	private Seccion seccion;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "esViento", columnDefinition = "boolean default true")
	private Boolean esViento;

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