package com.example.appBandas.dtos;

import java.util.List;

import com.example.appBandas.modelos.Banda;

public class EstadisticasInicioDTO {

	private long totalBandas;
	private long musicosTotales;
	private long ensayosHoy;
	private int cargaServidor;
	private String datosGrafico;
	private List<Banda> ultimasBandas;

	public EstadisticasInicioDTO() {
	}

	public long getTotalBandas() {
		return totalBandas;
	}

	public void setTotalBandas(long totalBandas) {
		this.totalBandas = totalBandas;
	}

	public long getMusicosTotales() {
		return musicosTotales;
	}

	public void setMusicosTotales(long musicosTotales) {
		this.musicosTotales = musicosTotales;
	}

	public long getEnsayosHoy() {
		return ensayosHoy;
	}

	public void setEnsayosHoy(long ensayosHoy) {
		this.ensayosHoy = ensayosHoy;
	}

	public int getCargaServidor() {
		return cargaServidor;
	}

	public void setCargaServidor(int cargaServidor) {
		this.cargaServidor = cargaServidor;
	}

	public String getDatosGrafico() {
		return datosGrafico;
	}

	public void setDatosGrafico(String datosGrafico) {
		this.datosGrafico = datosGrafico;
	}

	public List<Banda> getUltimasBandas() {
		return ultimasBandas;
	}

	public void setUltimasBandas(List<Banda> ultimasBandas) {
		this.ultimasBandas = ultimasBandas;
	}
}