package com.example.appBandas.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_cargos")
public class UsuarioCargo {

    @EmbeddedId
    private UsuarioCargoId id = new UsuarioCargoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCargo", insertable = false, updatable = false)
    private RolCargo cargo;

    public UsuarioCargo() {}

    public UsuarioCargoId getId() { return id; }
    public void setId(UsuarioCargoId id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public RolCargo getCargo() { return cargo; }
    public void setCargo(RolCargo cargo) { this.cargo = cargo; }
}