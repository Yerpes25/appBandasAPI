package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que vincula a un usuario ocupando una plaza en un coche compartido.
 */
@Entity
@Table(name = "Pasajeros_Vehiculo")
public class PasajeroVehiculo {

    @EmbeddedId
    private PasajeroVehiculoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idVehiculo")
    @JoinColumn(name = "idVehiculo")
    private VehiculoCompartido vehiculo;

    public PasajeroVehiculo() {
    }

    public PasajeroVehiculoId getId() {
        return id;
    }

    public void setId(PasajeroVehiculoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VehiculoCompartido getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoCompartido vehiculo) {
        this.vehiculo = vehiculo;
    }
}