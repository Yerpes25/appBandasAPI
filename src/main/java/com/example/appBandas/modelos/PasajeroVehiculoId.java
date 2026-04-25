package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para enlazar al pasajero con el vehiculo.
 */
@Embeddable
public class PasajeroVehiculoId implements Serializable {

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idVehiculo")
    private Integer idVehiculo;

    public PasajeroVehiculoId() {
    }

    public PasajeroVehiculoId(Integer idUsuario, Integer idVehiculo) {
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasajeroVehiculoId that = (PasajeroVehiculoId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idVehiculo, that.idVehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idVehiculo);
    }
}