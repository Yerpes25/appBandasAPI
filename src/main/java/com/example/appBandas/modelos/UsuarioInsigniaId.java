package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clave primaria compuesta para la tabla Usuarios_Insignias.
 */
@Embeddable
public class UsuarioInsigniaId implements Serializable {

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idInsignia")
    private Integer idInsignia;

    public UsuarioInsigniaId() {}

    public UsuarioInsigniaId(Integer idUsuario, Integer idInsignia) {
        this.idUsuario = idUsuario;
        this.idInsignia = idInsignia;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdInsignia() { return idInsignia; }
    public void setIdInsignia(Integer idInsignia) { this.idInsignia = idInsignia; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioInsigniaId that = (UsuarioInsigniaId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idInsignia, that.idInsignia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idInsignia);
    }
}