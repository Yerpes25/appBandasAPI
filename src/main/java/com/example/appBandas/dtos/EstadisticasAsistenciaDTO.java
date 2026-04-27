package com.example.appBandas.dtos;

public class EstadisticasAsistenciaDTO {
    private long asistidos;
    private long totales;
    private int porcentaje;

    public EstadisticasAsistenciaDTO() {}

    public long getAsistidos() { return asistidos; }
    public void setAsistidos(long asistidos) { this.asistidos = asistidos; }

    public long getTotales() { return totales; }
    public void setTotales(long totales) { this.totales = totales; }

    public int getPorcentaje() { return porcentaje; }
    public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }
}