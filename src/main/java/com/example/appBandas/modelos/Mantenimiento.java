package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "mantenimiento")
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

    @Column(name = "descripcion", length = 250)
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