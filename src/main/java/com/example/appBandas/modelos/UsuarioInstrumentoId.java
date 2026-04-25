package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta tridimensional para la tabla
 * Usuarios_Instrumentos. Vincula el identificador del usuario con el del
 * instrumento y su voz.
 */
@Embeddable
public class UsuarioInstrumentoId implements Serializable {

	@Column(name = "idUsuario")
	private Integer idUsuario;

	@Column(name = "idInstrumento")
	private Integer idInstrumento;

	@Column(name = "idVoz")
	private Integer idVoz;

	public UsuarioInstrumentoId() {
	}

	public UsuarioInstrumentoId(Integer idUsuario, Integer idInstrumento, Integer idVoz) {
		this.idUsuario = idUsuario;
		this.idInstrumento = idInstrumento;
		this.idVoz = idVoz;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdInstrumento() {
		return idInstrumento;
	}

	public void setIdInstrumento(Integer idInstrumento) {
		this.idInstrumento = idInstrumento;
	}

	public Integer getIdVoz() {
		return idVoz;
	}

	public void setIdVoz(Integer idVoz) {
		this.idVoz = idVoz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsuarioInstrumentoId that = (UsuarioInstrumentoId) o;
		return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idInstrumento, that.idInstrumento)
				&& Objects.equals(idVoz, that.idVoz);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario, idInstrumento, idVoz);
	}
}
