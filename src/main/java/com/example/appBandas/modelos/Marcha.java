package com.example.appBandas.modelos;

import java.time.LocalDate;

import jakarta.persistence.*;

/**
 * Entidad que representa las composiciones musicales del repertorio de la
 * banda. Almacena el nombre de la marcha y su autor.
 */
@Entity
@Table(name = "Marchas")
public class Marcha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMarcha")
	private Integer idMarcha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanda", nullable = false)
	private Banda banda;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "autor", length = 100)
	private String autor;

	@Column(name = "tiempo", length = 20)
	private String tiempo;

	@Column(name = "f_montaje")
	private LocalDate fMontaje;

	@Column(name = "categoria", length = 50)
	private String categoria;

	@Column(name = "estado", length = 50)
	private String estado;

	public Marcha() {
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public LocalDate getfMontaje() {
		return fMontaje;
	}

	public void setfMontaje(LocalDate fMontaje) {
		this.fMontaje = fMontaje;
	}

	public Integer getIdMarcha() {
		return idMarcha;
	}

	public void setIdMarcha(Integer idMarcha) {
		this.idMarcha = idMarcha;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}
