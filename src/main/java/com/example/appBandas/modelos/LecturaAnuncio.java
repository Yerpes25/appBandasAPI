package com.example.appBandas.modelos;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * Entidad que registra el momento exacto en el que un usuario lee un anuncio del tablon.
 */
@Entity
@Table(name = "Lecturas_Anuncios")
public class LecturaAnuncio {

    @EmbeddedId
    private LecturaAnuncioId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAnuncios")
    @JoinColumn(name = "idAnuncios")
    private TablonAnuncio anuncio;

    @JsonProperty("fLectura")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "f_lectura", nullable = false)
    private LocalDateTime fLectura;

    public LecturaAnuncio() {
    }

    public LecturaAnuncioId getId() {
        return id;
    }

    public void setId(LecturaAnuncioId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TablonAnuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(TablonAnuncio anuncio) {
        this.anuncio = anuncio;
    }

    public LocalDateTime getfLectura() {
        return fLectura;
    }

    public void setfLectura(LocalDateTime fLectura) {
        this.fLectura = fLectura;
    }
}