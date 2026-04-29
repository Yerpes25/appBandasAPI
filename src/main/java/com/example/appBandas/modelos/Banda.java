package com.example.appBandas.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "banda")
public class Banda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBanda")
	private Integer idBanda;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "identificador_banda", unique = true, length = 50)
	private String identificadorBanda;

	@Column(name = "foto_portada", length = 500)
	private String fotoPortada;

	@Column(name = "color_primario", length = 10)
	private String colorPrimario;

	@JsonProperty("fFundacion")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "f_fundacion")
	private LocalDate fFundacion;

	@Column(name = "direccion")
	private String direccion;

	@JsonProperty("ultimaConexion")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ultima_conexion")
	private LocalDate ultimaConexion;

	@JsonProperty("fechaRegistro")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_registro", updatable = false)
	private LocalDate fechaRegistro;

	@JsonIgnore
	@OneToMany(mappedBy = "banda", cascade = CascadeType.ALL)
	private List<Usuario> usuarios;

	@JsonIgnore
	@OneToMany(mappedBy = "banda", cascade = CascadeType.ALL)
	private List<Evento> eventos;

	@Column(name = "instagram")
	private String instagram;

	@Column(name = "twitter")
	private String twitter;

	@Column(name = "youtube")
	private String youtube;

	@Column(name = "activo")
	private Boolean activo = true;

	public Banda() {
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public LocalDate getUltimaConexion() {
		return ultimaConexion;
	}

	public void setUltimaConexion(LocalDate ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}

	public Integer getIdBanda() {
		return idBanda;
	}

	public void setIdBanda(Integer idBanda) {
		this.idBanda = idBanda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificadorBanda() {
		return identificadorBanda;
	}

	public void setIdentificadorBanda(String identificadorBanda) {
		this.identificadorBanda = identificadorBanda;
	}

	public String getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
	}

	public String getColorPrimario() {
		return colorPrimario;
	}

	public void setColorPrimario(String colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public LocalDate getfFundacion() {
		return fFundacion;
	}

	public void setfFundacion(LocalDate fFundacion) {
		this.fFundacion = fFundacion;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	/**
	 * Metodo que se ejecuta de forma automatica justo antes de guardar una nueva
	 * banda en la base de datos por primera vez.
	 */
	@PrePersist
	protected void alCrear() {
		if (this.fechaRegistro == null) {
			this.fechaRegistro = LocalDate.now(); // Pone el dia de hoy
		}
	}

	// Y recuerda anadir abajo del todo sus Getters y Setters:
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}