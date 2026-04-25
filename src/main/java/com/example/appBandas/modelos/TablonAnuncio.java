package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que representa el canal de comunicación oficial de la banda. Permite
 * publicar avisos que pueden requerir confirmación de lectura.
 */
@Entity
@Table(name = "TablonAnuncios")
public class TablonAnuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAnuncios")
	private Integer idAnuncios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanda", nullable = false)
	private Banda banda;

	@Column(name = "titulo", nullable = false, length = 100)
	private String titulo;

	@Column(name = "mensaje", columnDefinition = "TEXT", nullable = false)
	private String mensaje;

	@Column(name = "requiereConf", columnDefinition = "boolean default false")
	private Boolean requiereConf;

	public TablonAnuncio() {
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

	public Boolean getRequiereConf() {
		return requiereConf;
	}

	public void setRequiereConf(Boolean requiereConf) {
		this.requiereConf = requiereConf;
	}
}