package com.example.appBandas.dtos;

public class ComponenteDTO {
    private String nombreCompleto;
    private String fotoPerfil;
    private String instrumentoYVoz;
    private String cargo;

    public ComponenteDTO() {}

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public String getInstrumentoYVoz() { return instrumentoYVoz; }
    public void setInstrumentoYVoz(String instrumentoYVoz) { this.instrumentoYVoz = instrumentoYVoz; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}