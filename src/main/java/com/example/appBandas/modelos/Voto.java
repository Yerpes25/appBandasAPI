package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;

/**
 * Entidad que almacena la opción elegida por un usuario en un proceso de
 * votación. Relaciona al usuario con la votación y guarda la respuesta emitida.
 */
@Entity
@Table(name = "Votos")
public class Voto {

	@EmbeddedId
	private VotoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idVotacion")
	@JoinColumn(name = "idVotacion")
	private Votacion votacion;

	@Column(name = "opcion", nullable = false, length = 100)
	private String opcion;

	public Voto() {
	}

	public VotoId getId() {
		return id;
	}

	public void setId(VotoId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Votacion getVotacion() {
		return votacion;
	}

	public void setVotacion(Votacion votacion) {
		this.votacion = votacion;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
}