package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que registra la presencia de los músicos en los eventos. Almacena el
 * estado (Asiste, Falta, Justificado) y posibles observaciones.
 */
@Entity
@Table(name = "Asistencia")
public class Asistencia {

	@EmbeddedId
	private AsistenciaId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idEvento")
	@JoinColumn(name = "idEvento")
	private Evento evento;

	@Column(name = "estado", length = 20)
	private String estado;

	@Column(name = "observacion", length = 255)
	private String observacion;

	public Asistencia() {
	}

	public AsistenciaId getId() {
		return id;
	}

	public void setId(AsistenciaId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}