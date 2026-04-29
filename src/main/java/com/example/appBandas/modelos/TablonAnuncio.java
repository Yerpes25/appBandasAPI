package com.example.appBandas.modelos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tablon_anuncio")
public class TablonAnuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAnuncios")
	private Integer idAnuncios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanda", nullable = true)
	private Banda banda;

	@Column(name = "titulo", nullable = false, length = 100)
	private String titulo;

	@Column(name = "mensaje", length = 250, nullable = false)
	private String mensaje;

	@Column(name = "fechaExpira")
	private LocalDateTime fechaExpira;

	@Column(name = "tipo_destino", length = 20)
	private String tipoDestino = "BANDA";

	@Column(name = "id_usuario_destino")
	private Integer idUsuarioDestino;

	public TablonAnuncio() {
	}

	public String getTipoDestino() {
		return tipoDestino;
	}

	public void setTipoDestino(String tipoDestino) {
		this.tipoDestino = tipoDestino;
	}

	public Integer getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	public void setIdUsuarioDestino(Integer idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
	}

	public LocalDateTime getFechaExpira() {
		return fechaExpira;
	}

	public void setFechaExpira(LocalDateTime fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public Integer getIdAnuncios() {
		return idAnuncios;
	}

	public void setIdAnuncios(Integer idAnuncios) {
		this.idAnuncios = idAnuncios;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}