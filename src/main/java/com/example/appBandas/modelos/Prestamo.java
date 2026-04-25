package com.example.appBandas.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que controla la cesión de material del inventario a los músicos.
 * Registra la fecha de salida y la fecha de devolución del artículo.
 */
@Entity
@Table(name = "Prestamos")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPrestamo")
	private Integer idPrestamo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idArticulo", nullable = false)
	private Inventario inventario;

	@Column(name = "fSalida", nullable = false)
	private LocalDate fSalida;

	@Column(name = "fDevolucion")
	private LocalDate fDevolucion;

	public Prestamo() {
	}

	public Integer getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public LocalDate getfSalida() {
		return fSalida;
	}

	public void setfSalida(LocalDate fSalida) {
		this.fSalida = fSalida;
	}

	public LocalDate getfDevolucion() {
		return fDevolucion;
	}

	public void setfDevolucion(LocalDate fDevolucion) {
		this.fDevolucion = fDevolucion;
	}
}