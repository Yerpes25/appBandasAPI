package com.example.appBandas.controladores;

import com.example.appBandas.servicios.LogServicio;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 * Componente para monitorizar el ciclo de vida de la aplicacion.
 * Registra en la base de datos el momento exacto en el que el servidor
 * arranca o se detiene.
 */
@Component
public class MonitorSistema {

    private final LogServicio logServicio;

    public MonitorSistema(LogServicio logServicio) {
        this.logServicio = logServicio;
    }

    /**
     * Se ejecuta automaticamente cuando la aplicacion termina de arrancar.
     */
    @PostConstruct
    public void registrarArranque() {
        logServicio.registrarNuevoLog(
            "SISTEMA",
            "MonitorSistema",
            "Servidor iniciado correctamente",
            "La aplicacion esta en linea y operativa"
        );
    }

    /**
     * Se ejecuta justo antes de que el proceso de la aplicacion se detenga.
     */
    @PreDestroy
    public void registrarApagado() {
        logServicio.registrarNuevoLog(
            "SISTEMA",
            "MonitorSistema",
            "Apagado del servidor detectado",
            "El proceso ha recibido una orden de parada"
        );
    }
}