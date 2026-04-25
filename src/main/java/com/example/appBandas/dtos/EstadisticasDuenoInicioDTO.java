package com.example.appBandas.dtos;

import java.util.List;
import java.util.Map;

public class EstadisticasDuenoInicioDTO {

	// Tarjeta 1: Músicos
	private long totalMusicos;
	private long musicosActivos;

	// Tarjeta 2: Ensayos
	private long totalEnsayos;
	private String ensayosComparativa;

	// Tarjeta 3: Ingresos
	private double ingresosTrimestral;
	private String porcentajeIngresos;
	private String estadoIngresos;

	// Tablas Inferiores
	private List<Map<String, String>> marchasNuevas;
	private List<Map<String, String>> inventarioEscaso;

	public EstadisticasDuenoInicioDTO() {
	}

	// GETTERS Y SETTERS
	public long getTotalMusicos() {
		return totalMusicos;
	}

	public void setTotalMusicos(long totalMusicos) {
		this.totalMusicos = totalMusicos;
	}

	public long getMusicosActivos() {
		return musicosActivos;
	}

	public void setMusicosActivos(long musicosActivos) {
		this.musicosActivos = musicosActivos;
	}

	public long getTotalEnsayos() {
		return totalEnsayos;
	}

	public void setTotalEnsayos(long totalEnsayos) {
		this.totalEnsayos = totalEnsayos;
	}

	public String getEnsayosComparativa() {
		return ensayosComparativa;
	}

	public void setEnsayosComparativa(String ensayosComparativa) {
		this.ensayosComparativa = ensayosComparativa;
	}

	public double getIngresosTrimestral() {
		return ingresosTrimestral;
	}

	public void setIngresosTrimestral(double ingresosTrimestral) {
		this.ingresosTrimestral = ingresosTrimestral;
	}

	public String getPorcentajeIngresos() {
		return porcentajeIngresos;
	}

	public void setPorcentajeIngresos(String porcentajeIngresos) {
		this.porcentajeIngresos = porcentajeIngresos;
	}

	public String getEstadoIngresos() {
		return estadoIngresos;
	}

	public void setEstadoIngresos(String estadoIngresos) {
		this.estadoIngresos = estadoIngresos;
	}

	public List<Map<String, String>> getMarchasNuevas() {
		return marchasNuevas;
	}

	public void setMarchasNuevas(List<Map<String, String>> marchasNuevas) {
		this.marchasNuevas = marchasNuevas;
	}

	public List<Map<String, String>> getInventarioEscaso() {
		return inventarioEscaso;
	}

	public void setInventarioEscaso(List<Map<String, String>> inventarioEscaso) {
		this.inventarioEscaso = inventarioEscaso;
	}
}