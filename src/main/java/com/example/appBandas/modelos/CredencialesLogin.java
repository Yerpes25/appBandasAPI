package com.example.appBandas.modelos;

/**
 * Clase que sirve como contenedor para recibir los datos de inicio de sesion.
 * Captura el correo y la clave que envia la aplicacion cliente en formato JSON
 * para que el controlador pueda leerlos facilmente.
 */
public class CredencialesLogin {
    
    private String correo;
    private String clave;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
