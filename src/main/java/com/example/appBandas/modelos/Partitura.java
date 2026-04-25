package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que almacena la ubicación de los archivos digitales de música.
 * Vincula cada documento PDF o audio de referencia con una marcha específica.
 */
@Entity
@Table(name = "Partituras")
public class Partitura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPartituras")
	private Integer idPartituras;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMarcha", nullable = false)
	private Marcha marcha;

	@Column(name = "rutaPDF", length = 255)
	private String rutaPDF;

	@Column(name = "rutaAudio", length = 255)
	private String rutaAudio;

	public Partitura() {
	}

	public Integer getIdPartituras() {
		return idPartituras;
	}

	public void setIdPartituras(Integer idPartituras) {
		this.idPartituras = idPartituras;
	}

	public Marcha getMarcha() {
		return marcha;
	}

	public void setMarcha(Marcha marcha) {
		this.marcha = marcha;
	}

	public String getRutaPDF() {
		return rutaPDF;
	}

	public void setRutaPDF(String rutaPDF) {
		this.rutaPDF = rutaPDF;
	}

	public String getRutaAudio() {
		return rutaAudio;
	}

	public void setRutaAudio(String audio) {
		this.rutaAudio = audio;
	}
}