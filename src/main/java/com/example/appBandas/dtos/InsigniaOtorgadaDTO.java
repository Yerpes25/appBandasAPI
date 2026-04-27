package com.example.appBandas.dtos;

import java.time.LocalDate;

public class InsigniaOtorgadaDTO {
	private String titulo;
	private String icono;
	private Integer meta;
	private LocalDate fechaOtorgada;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Integer getMeta() {
		return meta;
	}

	public void setMeta(Integer meta) {
		this.meta = meta;
	}

	public LocalDate getFechaOtorgada() {
		return fechaOtorgada;
	}

	public void setFechaOtorgada(LocalDate fechaOtorgada) {
		this.fechaOtorgada = fechaOtorgada;
	}
}