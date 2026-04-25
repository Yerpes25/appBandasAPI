package com.example.appBandas.modelos;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Entidad para registrar los avisos de reparacion o mantenimiento 
 * de los articulos del inventario (instrumentos, uniformes).
 */
@Entity
@Table(name = "Mantenimiento")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMantenimiento")
    private Integer idMantenimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArticulo", nullable = false)
    private Inventario articulo;

    @JsonProperty("fAviso")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fAviso", nullable = false)
    private LocalDate fAviso;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public Mantenimiento() {
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Inventario getArticulo() {
        return articulo;
    }

    public void setArticulo(Inventario articulo) {
        this.articulo = articulo;
    }

    public LocalDate getfAviso() {
        return fAviso;
    }

    public void setfAviso(LocalDate fAviso) {
        this.fAviso = fAviso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}