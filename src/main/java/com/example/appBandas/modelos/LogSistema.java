package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs_sistema")
public class LogSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log")
	private Long idLog;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "nivel", nullable = false, length = 10)
	private String nivel; 

	@Column(name = "origen", length = 50)
	private String origen; 

	@Column(name = "mensaje", length = 1000)
	private String mensaje;

	@Column(name = "contexto", length = 100)
	private String contexto; 

	public LogSistema() {
		this.fecha = LocalDateTime.now();
	}

	public LogSistema(String nivel, String origen, String mensaje, String contexto) {
		this.fecha = LocalDateTime.now();
		this.nivel = nivel;
		this.origen = origen;
		this.mensaje = mensaje;
		this.contexto = contexto;
	}

	public Long getIdLog() {
		return idLog;
	}

	public void setIdLog(Long idLog) {
		this.idLog = idLog;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
}