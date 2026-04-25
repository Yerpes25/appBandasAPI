package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad intermedia que asigna a un usuario qué instrumento toca y en qué voz.
 * También almacena atributos específicos de esta asignación como si es solista
 * o lleva maza.
 */
@Entity
@Table(name = "Usuarios_Instrumentos")
public class UsuarioInstrumento {

	@EmbeddedId
	private UsuarioInstrumentoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "idInstrumento", referencedColumnName = "idInstrumento", insertable = false, updatable = false),
			@JoinColumn(name = "idVoz", referencedColumnName = "idVoz", insertable = false, updatable = false) })
	private InstrumentoVoz instrumentoVoz;

	@Column(name = "es_solista", columnDefinition = "boolean default false")
	private Boolean esSolista;

	@Column(name = "es_maza", columnDefinition = "boolean default false")
	private Boolean esMaza;

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