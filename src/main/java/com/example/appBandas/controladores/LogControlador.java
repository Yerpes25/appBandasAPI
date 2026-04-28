package com.example.appBandas.controladores;

import com.example.appBandas.modelos.LogSistema;
import com.example.appBandas.servicios.LogServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para exponer la informacion de los logs del sistema.
 * Proporciona endpoints para que la interfaz de usuario pueda recuperar
 * el historial de eventos y pintar las estadisticas del panel de administracion.
 */
@RestController
@RequestMapping("/api/logs")
public class LogControlador {

    private final LogServicio logServicio;

    // Inyeccion de dependencias manual mediante constructor
    public LogControlador(LogServicio logServicio) {
        this.logServicio = logServicio;
    }

    // Endpoint para solicitar el listado de los 50 logs mas recientes
    @GetMapping("/recientes")
    public List<LogSistema> obtenerRecientes() {
        return logServicio.obtenerLogsRecientes();
    }

    // Endpoint para solicitar los datos agrupados para tarjetas de estadisticas
    @GetMapping("/estadisticas")
    public Map<String, Long> obtenerEstadisticas() {
        // Se crea un mapa manualmente para devolver las tres metricas en formato clave-valor
        Map<String, Long> estadisticas = new HashMap<>();
        
        long erroresHoy = logServicio.contarErroresUltimas24h();
        long erroresAyer = logServicio.contarErroresDiaAnterior();
        
        String tendencia = "0%";
        if (erroresAyer > 0) {
            long diferencia = erroresHoy - erroresAyer;
            long porcentaje = (diferencia * 100) / erroresAyer;
            tendencia = (porcentaje > 0 ? "+" : "") + porcentaje + "%";
        }
        
        estadisticas.put("totalErrores24h", erroresHoy);
        estadisticas.put("tendenciaPorcentual", Long.valueOf(tendencia));
        estadisticas.put("totalEventos24h", logServicio.contarTotalEventos24h());
        
        return estadisticas;
    }
    
    /**
     * Proporciona el historico de reinicios del sistema agrupados por mes.
     * Util para detectar inestabilidad en el despliegue de Clever Cloud.
     */
    @GetMapping("/reinicios-mensuales")
    public Map<String, Long> obtenerReinicios() {
        return logServicio.consultarReiniciosMensuales();
    }
}