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

@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBanda", nullable = false)
    private Banda banda;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double cantidad; 

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipo; 

    public Transaccion() {}

    public Integer getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(Integer idTransaccion) { this.idTransaccion = idTransaccion; }
    public Banda getBanda() { return banda; }
    public void setBanda(Banda banda) { this.banda = banda; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}