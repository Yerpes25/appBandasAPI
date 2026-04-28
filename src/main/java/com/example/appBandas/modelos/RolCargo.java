package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol_cargo")
public class RolCargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCargo")
	private Integer idCargo;

	@Column(name = "Nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "tipoCargo", length = 50)
	private String tipoCargo;

	@Column(name = "LimitePersonas")
	private Integer limitePersonas;

	public RolCargo() {
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Integer getLimitePersonas() {
		return limitePersonas;
	}

	public void setLimitePersonas(Integer limitePersonas) {
		this.limitePersonas = limitePersonas;
	}
}