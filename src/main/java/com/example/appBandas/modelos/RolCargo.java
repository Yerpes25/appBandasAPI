package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que define los cargos oficiales dentro de la banda (Director,
 * Tesorero, etc.). Controla el nombre del cargo, el tipo y el límite de
 * personas que pueden ocuparlo.
 */
@Entity
@Table(name = "rolesCargos")
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