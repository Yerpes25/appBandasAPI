package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para asignar roles a la junta de la banda.
 */
@Embeddable
public class UsuarioCargoId implements Serializable {

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idCargo")
    private Integer idCargo;

    public UsuarioCargoId() {}

    public UsuarioCargoId(Integer idUsuario, Integer idCargo) {
        this.idUsuario = idUsuario;
        this.idCargo = idCargo;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdCargo() { return idCargo; }
    public void setIdCargo(Integer idCargo) { this.idCargo = idCargo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioCargoId that = (UsuarioCargoId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idCargo, that.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idCargo);
    }
}