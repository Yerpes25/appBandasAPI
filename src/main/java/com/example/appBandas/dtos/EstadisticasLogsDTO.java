package com.example.appBandas.dtos;

import java.util.List;

import com.example.appBandas.modelos.LogSistema;

public class EstadisticasLogsDTO {
	// Tarjeta 1
	private long totalErrores24h;
	private String tendenciaPorcentual;

	// Tarjeta 2
	private String tiempoActividad; // Ej: "99.98%"
	private String estadoActividad; // Ej: "Estable" o "Mantenimiento"

	// Tarjeta 3
	private String latenciaMedia; // Ej: "24"
	private String mejoraLatencia; // Ej: "-5ms mejora" o "+2ms peor"

	private java.util.List<LogSistema> ultimosLogs;

	public EstadisticasLogsDTO() {
	}

	// Getters y Setters
	public long getTotalErrores24h() {
		return totalErrores24h;
	}

	public void setTotalErrores24h(long totalErrores24h) {
		this.totalErrores24h = totalErrores24h;
	}

	public String getTendenciaPorcentual() {
		return tendenciaPorcentual;
	}

	public void setTendenciaPorcentual(String tendenciaPorcentual) {
		this.tendenciaPorcentual = tendenciaPorcentual;
	}

	public String getTiempoActividad() {
		return tiempoActividad;
	}

	public void setTiempoActividad(String tiempoActividad) {
		this.tiempoActividad = tiempoActividad;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public String getLatenciaMedia() {
		return latenciaMedia;
	}

	public void setLatenciaMedia(String latenciaMedia) {
		this.latenciaMedia = latenciaMedia;
	}

	public String getMejoraLatencia() {
		return mejoraLatencia;
	}

	public void setMejoraLatencia(String mejoraLatencia) {
		this.mejoraLatencia = mejoraLatencia;
	}

	public java.util.List<LogSistema> getUltimosLogs() {
		return ultimosLogs;
	}

	public void setUltimosLogs(List<LogSistema> ultimosLogs) {
		this.ultimosLogs = ultimosLogs;
	}
}