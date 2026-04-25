package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que define la clave primaria compuesta para la tabla de Asistencia.
 * Vincula de forma única a un usuario con un evento específico.
 */
@Embeddable
public class AsistenciaId implements Serializable {

	@Column(name = "idUsuario")
	private Integer idUsuario;

	@Column(name = "idEvento")
	private Integer idEvento;

	public AsistenciaId() {
	}

	public AsistenciaId(Integer idUsuario, Integer idEvento) {
		this.idUsuario = idUsuario;
		this.idEvento = idEvento;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AsistenciaId that = (AsistenciaId) o;
		return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idEvento, that.idEvento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario, idEvento);
	}
}