package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que vincula a un musico con su puesto oficial dentro de la asociacion.
 */
@Entity
@Table(name = "Usuarios_Cargos")
public class UsuarioCargo {

    @EmbeddedId
    private UsuarioCargoId id = new UsuarioCargoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCargo")
    @JoinColumn(name = "idCargo")
    private RolCargo cargo;

    public UsuarioCargo() {}

    public UsuarioCargoId getId() { return id; }
    public void setId(UsuarioCargoId id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public RolCargo getCargo() { return cargo; }
    public void setCargo(RolCargo cargo) { this.cargo = cargo; }
}