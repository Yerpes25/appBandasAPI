package com.example.appBandas.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Entidad que enlaza a un musico principal (banderin/abanderado) con otro que lleva la borla.
 */
@Entity
@Table(name = "Asignacion_Borlas")
public class AsignacionBorla {

    @EmbeddedId
    private AsignacionBorlaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAbanderado")
    @JoinColumn(name = "idAbanderado")
    private Usuario abanderado;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBorla")
    @JoinColumn(name = "idBorla")
    private Usuario borla;

    public AsignacionBorla() {
    }

    public AsignacionBorlaId getId() {
        return id;
    }

    public void setId(AsignacionBorlaId id) {
        this.id = id;
    }

    public Usuario getAbanderado() {
        return abanderado;
    }

    public void setAbanderado(Usuario abanderado) {
        this.abanderado = abanderado;
    }

    public Usuario getBorla() {
        return borla;
    }

    public void setBorla(Usuario borla) {
        this.borla = borla;
    }
}