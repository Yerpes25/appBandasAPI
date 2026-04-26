package com.example.appBandas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appBandas.dtos.EstadisticasInicioDTO;
import com.example.appBandas.dtos.EstadisticasLogsDTO;
import com.example.appBandas.repositorios.LogRepository;
import com.example.appBandas.servicios.AdminServicio;

/**
 * Controlador encargado de recopilar metricas reales del sistema. Calcula el
 * estado de salud y rendimiento basandose en los logs de MySQL.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminControlador {

	@Autowired
	private LogRepository logRepo;
	private final AdminServicio adminServicio;
	
	public AdminControlador(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

	// =======================================================
	// RUTA 1: PARA LA PANTALLA DE INICIO (DASHBOARD)
	// =======================================================
	@GetMapping("/estadisticas-inicio")
	public ResponseEntity<EstadisticasInicioDTO> obtenerDashboard() {
		return ResponseEntity.ok(adminServicio.obtenerEstadisticasInicio());
	}

	@GetMapping("/estadisticas-logs")
	public EstadisticasLogsDTO obtenerEstadisticasLogs() {
		// 1. OBTENEMOS DATOS REALES DE LA BASE DE DATOS
		long erroresHoy = logRepo.contarErroresUltimas24h();
		long erroresAyer = logRepo.contarErroresDiaAnterior();
		long totalEventos = logRepo.contarTotalEventos24h();

		EstadisticasLogsDTO maleta = new EstadisticasLogsDTO();

		// --- TARJETA 1: ERRORES REALES ---
		maleta.setTotalErrores24h(erroresHoy);
		// Calculo de tendencia porcentual
		if (erroresAyer == 0) {
			maleta.setTendenciaPorcentual(erroresHoy > 0 ? "+100% vs ayer" : "0% vs ayer");
		} else {
			long diferencia = erroresHoy - erroresAyer;
			long porcentaje = (diferencia * 100) / erroresAyer;
			maleta.setTendenciaPorcentual((diferencia > 0 ? "+" : "") + porcentaje + "% vs ayer");
		}

		// --- TARJETA 2: ACTIVIDAD REAL (SALUD) ---
		// El Uptime real es: (Eventos Exitosos / Eventos Totales) * 100
		double salud = 100.00;
		if (totalEventos > 0) {
			long exitos = totalEventos - erroresHoy;
			salud = ((double) exitos / totalEventos) * 100;
		}
		maleta.setTiempoActividad(String.format("%.2f%%", salud).replace(",", "."));

		// Determinamos el estado segun la salud real
		if (salud >= 99.0)
			maleta.setEstadoActividad("Estable");
		else if (salud >= 95.0)
			maleta.setEstadoActividad("Aviso");
		else
			maleta.setEstadoActividad("Crítico");

		// --- TARJETA 3: LATENCIA REAL (VELOCIDAD DISCO/RED) ---
		long inicio = System.currentTimeMillis();
		logRepo.count();
		long latenciaReal = System.currentTimeMillis() - inicio;

		maleta.setLatenciaMedia(latenciaReal + "ms");

		// Comparamos con una latencia base de 30ms para ver si mejora o empeora
		long latenciaBase = 30;
		long diferenciaLat = latenciaReal - latenciaBase;
		maleta.setMejoraLatencia((diferenciaLat <= 0 ? "" : "+") + diferenciaLat + "ms vs base");
		maleta.setUltimosLogs(logRepo.findTop50ByOrderByFechaDesc());

		return maleta;
	}
}