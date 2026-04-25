package com.example.appBandas.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidad que representa a una banda de musica en el sistema.
 * Sirve como eje central para agrupar usuarios, eventos y recursos.
 * Incluye un identificador unico de acceso para los componentes y 
 * una fotografia de portada opcional para el perfil.
 */
@Entity
@Table(name = "bandas")
public class Banda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banda")
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

	public Banda() {
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
     * Metodo que se ejecuta de forma automatica justo antes de guardar 
     * una nueva banda en la base de datos por primera vez.
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