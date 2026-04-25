package com.example.appBandas.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidad que gestiona los procesos participativos de la banda. Define el tema
 * a votar, la fecha límite y si el proceso sigue abierto.
 */
@Entity
@Table(name = "Votaciones")
public class Votacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVotacion")
	private Integer idVotacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanda", nullable = false)
	private Banda banda;

	@Column(name = "titulo", nullable = false, length = 150)
	private String titulo;

	@JsonProperty("fLimite")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "fLimite", nullable = false)
	private LocalDateTime fLimite;

	@Column(name = "activa", columnDefinition = "boolean default true")
	private Boolean activa;

	public Votacion() {
	}

	public Integer getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(Integer idVotacion) {
		this.idVotacion = idVotacion;
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

	public LocalDateTime getfLimite() {
		return fLimite;
	}

	public void setfLimite(LocalDateTime fLimite) {
		this.fLimite = fLimite;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
}