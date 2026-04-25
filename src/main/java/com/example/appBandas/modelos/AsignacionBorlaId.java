package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria para asociar abanderados y escoltas/borlas.
 */
@Embeddable
public class AsignacionBorlaId implements Serializable {

    @Column(name = "idAbanderado")
    private Integer idAbanderado;

    @Column(name = "idBorla")
    private Integer idBorla;

    public AsignacionBorlaId() {
    }

    public AsignacionBorlaId(Integer idAbanderado, Integer idBorla) {
        this.idAbanderado = idAbanderado;
        this.idBorla = idBorla;
    }

    public Integer getIdAbanderado() {
        return idAbanderado;
    }

    public void setIdAbanderado(Integer idAbanderado) {
        this.idAbanderado = idAbanderado;
    }

    public Integer getIdBorla() {
        return idBorla;
    }

    public void setIdBorla(Integer idBorla) {
        this.idBorla = idBorla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignacionBorlaId that = (AsignacionBorlaId) o;
        return Objects.equals(idAbanderado, that.idAbanderado) && Objects.equals(idBorla, that.idBorla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbanderado, idBorla);
    }
}