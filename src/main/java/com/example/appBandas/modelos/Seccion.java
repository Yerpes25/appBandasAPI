package com.example.appBandas.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "seccion")
public class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSeccion")
	private Integer idSeccion;

	@Column(name = "Nombre", nullable = false, length = 50)
	private String nombre;

	@JsonIgnore
	@OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
	private List<Instrumento> instrumentos;

	// Constructores
	public Seccion() {
	}

	// Getters y Setters
	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Instrumento> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}
}
