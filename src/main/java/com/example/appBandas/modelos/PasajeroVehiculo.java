package com.example.appBandas.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pasajero_vehiculo")
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