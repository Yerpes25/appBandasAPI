package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que gestiona las aportaciones económicas mensuales de los usuarios.
 * Permite llevar un control de los pagos pendientes y realizados.
 */
@Entity
@Table(name = "Cuotas")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCuota")
    private Integer idCuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "importe", nullable = false)
    private Double importe;

    @Column(name = "estado", length = 20)
    private String estado;

    public Cuota() {}

    public Integer getIdCuota() { return idCuota; }
    public void setIdCuota(Integer idCuota) { this.idCuota = idCuota; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}