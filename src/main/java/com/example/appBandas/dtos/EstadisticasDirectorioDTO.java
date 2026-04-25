package com.example.appBandas.dtos;

/**
 * Objeto de Transferencia de Datos (DTO).
 * Funciona como una "maleta" temporal para enviar todas las estadisticas 
 * de la pantalla del directorio en una sola peticion por internet.
 */
public class EstadisticasDirectorioDTO {
    
    private long totalBandas;
    private String tendencia;
    private long inactivas;
    private long nuevas24h;

    public EstadisticasDirectorioDTO() {
    }

    public long getTotalBandas() {
        return totalBandas;
    }

    public void setTotalBandas(long totalBandas) {
        this.totalBandas = totalBandas;
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        this.tendencia = tendencia;
    }

    public long getInactivas() {
        return inactivas;
    }

    public void setInactivas(long inactivas) {
        this.inactivas = inactivas;
    }

    public long getNuevas24h() {
        return nuevas24h;
    }

    public void setNuevas24h(long nuevas24h) {
        this.nuevas24h = nuevas24h;
    }
}