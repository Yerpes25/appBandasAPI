package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad para gestionar los coches que los usuarios ponen a disposicion
 * de otros musicos para viajar juntos a los eventos.
 */
@Entity
@Table(name = "VehiculosCompartidos")
public class VehiculoCompartido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehiculo")
    private Integer idVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvento", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "plazasTotales", nullable = false)
    private Integer plazasTotales;

    @Column(name = "plazasDisponibles", nullable = false)
    private Integer plazasDisponibles;

    public VehiculoCompartido() {
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPlazasTotales() {
        return plazasTotales;
    }

    public void setPlazasTotales(Integer plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public Integer getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(Integer plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }
}