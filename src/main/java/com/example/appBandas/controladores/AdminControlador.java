package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appBandas.dtos.EstadisticasInicioDTO;
import com.example.appBandas.dtos.EstadisticasLogsDTO;
import com.example.appBandas.servicios.AdminServicio;

/**
 * Controlador encargado de exponer los datos estadisticos al frontal.
 * Delega toda la logica de negocio en el AdminServicio para mantener
 * una arquitectura limpia.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminControlador {

    private final AdminServicio adminServicio;
    
    // Inyeccion unica y manual del servicio
    public AdminControlador(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

    @GetMapping("/estadisticas-inicio")
    public ResponseEntity<EstadisticasInicioDTO> obtenerDashboard() {
        return ResponseEntity.ok(adminServicio.obtenerEstadisticasInicio());
    }

    @GetMapping("/estadisticas-logs")
    public ResponseEntity<EstadisticasLogsDTO> obtenerEstadisticasLogs() {
        // Toda la recoleccion de datos y logica matematica la realiza el servicio
        return ResponseEntity.ok(adminServicio.obtenerEstadisticasLogs());
    }
}