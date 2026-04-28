package com.example.appBandas.controladores;

import com.example.appBandas.modelos.LogSistema;
import com.example.appBandas.servicios.LogServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para exponer la informacion de los logs del sistema al panel de JavaFX.
 * Incluye calculos reales de tiempo de actividad basados en el registro de errores.
 */
@RestController
@RequestMapping("/api/logs")
public class LogControlador {

    private final LogServicio logServicio;

    public LogControlador(LogServicio logServicio) {
        this.logServicio = logServicio;
    }

    @GetMapping("/recientes")
    public List<LogSistema> obtenerRecientes() {
        return logServicio.obtenerLogsRecientes();
    }
    
    @GetMapping("/reinicios-mensuales")
    public Map<String, Long> obtenerReinicios() {
        return logServicio.consultarReiniciosMensuales();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLog(@PathVariable Long id) {
        logServicio.eliminarLog(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/estadisticas")
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        
        long erroresHoy = logServicio.contarErroresUltimas24h();
        long erroresAyer = logServicio.contarErroresDiaAnterior();
        long totalEventos = logServicio.contarTotalEventos24h();
        
        String tendencia = "0%";
        if (erroresAyer > 0) {
            long diferencia = erroresHoy - erroresAyer;
            long porcentaje = (diferencia * 100) / erroresAyer;
            tendencia = (porcentaje > 0 ? "+" : "") + porcentaje + "%";
        } else if (erroresHoy > 0) {
            tendencia = "+100%"; 
        }
        
        double porcentajeUptime = 100.0;
        if (totalEventos > 0) {
            porcentajeUptime = 100.0 - (((double) erroresHoy / totalEventos) * 100.0);
        }

        // --- NUEVA LOGICA DE LATENCIA REAL ---
        long mediaHoy = logServicio.calcularLatenciaMedia(logServicio.obtenerLatenciasHoy());
        long mediaAyer = logServicio.calcularLatenciaMedia(logServicio.obtenerLatenciasAyer());

        String mejoraLatencia = "-";
        if (mediaAyer > 0 && mediaHoy > 0) {
            long diferencia = mediaAyer - mediaHoy; // Si hoy es menor, es positivo (mejora)
            long porcentajeMejora = (diferencia * 100) / mediaAyer;
            mejoraLatencia = (porcentajeMejora > 0 ? "+" : "") + porcentajeMejora + "%";
        }

        estadisticas.put("totalErrores24h", erroresHoy);
        estadisticas.put("tendenciaPorcentual", tendencia);
        estadisticas.put("totalEventos24h", totalEventos);
        estadisticas.put("tiempoActividad", String.format("%.1f%%", porcentajeUptime));
        estadisticas.put("estadoActividad", porcentajeUptime > 95.0 ? "Estable" : "Alerta");
        
        estadisticas.put("latenciaMedia", mediaHoy > 0 ? mediaHoy + " ms" : "0 ms");
        estadisticas.put("mejoraLatencia", mejoraLatencia);
        
        return estadisticas;
    }
}