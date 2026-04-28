package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_instrumento")
public class UsuarioInstrumento {

	@EmbeddedId
	private UsuarioInstrumentoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "idInstrumento", insertable = false, updatable = false),
			@JoinColumn(name = "idVoz", insertable = false, updatable = false) })
	private InstrumentoVoz instrumentoVoz;

	@Column(name = "es_solista")
	private Boolean esSolista = false;

	@Column(name = "es_maza")
	private Boolean esMaza = false;

	public UsuarioInstrumento() {
	}

	public UsuarioInstrumentoId getId() {
		return id;
	}

	public void setId(UsuarioInstrumentoId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public InstrumentoVoz getInstrumentoVoz() {
		return instrumentoVoz;
	}

	public void setInstrumentoVoz(InstrumentoVoz instrumentoVoz) {
		this.instrumentoVoz = instrumentoVoz;
	}

	public Boolean getEsSolista() {
		return esSolista;
	}

	public void setEsSolista(Boolean esSolista) {
		this.esSolista = esSolista;
	}

	public Boolean getEsMaza() {
		return esMaza;
	}

	public void setEsMaza(Boolean esMaza) {
		this.esMaza = esMaza;
	}
}