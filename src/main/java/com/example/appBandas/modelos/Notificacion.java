package com.example.appBandas.modelos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_notificacion")
	private Integer idNotificacion;

	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;

	@Column(name = "titulo", nullable = false, length = 100)
	private String titulo;

	@Column(name = "mensaje", length = 250, nullable = false)
	private String mensaje;

	@Column(name = "fecha_envio", nullable = false)
	private LocalDateTime fechaEnvio;

	@Column(name = "leida")
	private Boolean leida = false;

	@Column(name = "activo")
	private Boolean activo = true;

	public Notificacion() {
		this.fechaEnvio = LocalDateTime.now();
	}

	// Getters y Setters
	public Integer getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Integer idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Boolean getLeida() {
		return leida;
	}

	public void setLeida(Boolean leida) {
		this.leida = leida;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}