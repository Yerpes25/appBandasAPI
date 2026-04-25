package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta para la tabla Lecturas_Anuncios.
 * Une el ID del usuario con el ID del anuncio.
 */
@Embeddable
public class LecturaAnuncioId implements Serializable {

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idAnuncios")
    private Integer idAnuncios;

    public LecturaAnuncioId() {
    }

    public LecturaAnuncioId(Integer idUsuario, Integer idAnuncios) {
        this.idUsuario = idUsuario;
        this.idAnuncios = idAnuncios;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdAnuncios() {
        return idAnuncios;
    }

    public void setIdAnuncios(Integer idAnuncios) {
        this.idAnuncios = idAnuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LecturaAnuncioId that = (LecturaAnuncioId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idAnuncios, that.idAnuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idAnuncios);
    }
}