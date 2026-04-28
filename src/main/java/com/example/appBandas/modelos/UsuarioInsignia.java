package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidad que registra cuando un usuario desbloquea o consigue un
 * logro/insignia.
 */
@Entity
@Table(name = "usuario_insignia")
public class UsuarioInsignia {

	@EmbeddedId
	private UsuarioInsigniaId id = new UsuarioInsigniaId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idInsignia")
	@JoinColumn(name = "idInsignia", referencedColumnName = "idInsignia")
	private Insignia insignia;

	@JsonProperty("fObtencion")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "f_obtencion", nullable = false)
	private LocalDate fObtencion;

	public UsuarioInsignia() {
	}

	public UsuarioInsigniaId getId() {
		return id;
	}

	public void setId(UsuarioInsigniaId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Insignia getInsignia() {
		return insignia;
	}

	public void setInsignia(Insignia insignia) {
		this.insignia = insignia;
	}

	public LocalDate getfObtencion() {
		return fObtencion;
	}

	public void setfObtencion(LocalDate fObtencion) {
		this.fObtencion = fObtencion;
	}
}