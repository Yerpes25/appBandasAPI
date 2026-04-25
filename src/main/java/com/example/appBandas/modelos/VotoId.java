package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que define la clave primaria compuesta para la tabla de Votos. Asegura
 * que un usuario solo pueda emitir un voto por votación.
 */
@Embeddable
public class VotoId implements Serializable {

	@Column(name = "idUsuario")
	private Integer idUsuario;

	@Column(name = "idVotacion")
	private Integer idVotacion;

	public VotoId() {
	}

	public VotoId(Integer idUsuario, Integer idVotacion) {
		this.idUsuario = idUsuario;
		this.idVotacion = idVotacion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Integer idVotacion) {
		this.idVotacion = idVotacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VotoId votoId = (VotoId) o;
		return Objects.equals(idUsuario, votoId.idUsuario) && Objects.equals(idVotacion, votoId.idVotacion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario, idVotacion);
	}
}