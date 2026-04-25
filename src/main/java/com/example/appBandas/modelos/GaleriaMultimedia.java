package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad para almacenar las rutas de fotos, videos o audios 
 * asociados a un evento o actuacion especifica.
 */
@Entity
@Table(name = "galeriaMultimedia")
public class GaleriaMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArchivo")
    private Integer idArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvento", nullable = false)
    private Evento evento;

    @Column(name = "rutaArchivo", nullable = false, length = 255)
    private String rutaArchivo;

    @Column(name = "tipo", length = 20)
    private String tipo;

    public GaleriaMultimedia() {
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}