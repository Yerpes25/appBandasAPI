package com.example.appBandas.servicios;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.EstadisticasInicioDTO;
import com.example.appBandas.dtos.EstadisticasLogsDTO;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.LogRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

/**
 * Servicio encargado de gestionar toda la logica de negocio del panel de administracion.
 * Actua como cerebro unificando las llamadas a distintos repositorios.
 */
@Service
public class AdminServicio {

    private final BandaRepository bandaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;
    private final LogRepository logRepository; // Añadimos el repositorio de logs

    // Inyectamos todos los repositorios necesarios manualmente
    public AdminServicio(BandaRepository bandaRepository, UsuarioRepository usuarioRepository, 
                         EventoRepository eventoRepository, LogRepository logRepository) {
        this.bandaRepository = bandaRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
        this.logRepository = logRepository;
    }

    /**
     * Llena la Super-Maleta con todos los datos del Dashboard Principal.
     */
    public EstadisticasInicioDTO obtenerEstadisticasInicio() {
        EstadisticasInicioDTO maleta = new EstadisticasInicioDTO();
        
        maleta.setTotalBandas(bandaRepository.contarTodasLasBandas());
        maleta.setMusicosTotales(usuarioRepository.contarTodosLosUsuarios());
        maleta.setEnsayosHoy(eventoRepository.contarEnsayosDeBandas());
        maleta.setUltimasBandas(bandaRepository.findTop5ByOrderByFechaRegistroDesc());
        
        Runtime entorno = Runtime.getRuntime();
        long memoriaTotal = entorno.totalMemory();
        long memoriaUsada = memoriaTotal - entorno.freeMemory();
        maleta.setCargaServidor((int) ((memoriaUsada * 100) / memoriaTotal));
        
        maleta.setDatosGrafico(generarTextoGrafico());
        
        return maleta;
    }

    /**
     * Calcula y empaqueta las estadisticas reales de los logs del sistema.
     * Toda la logica matematica que estaba en el controlador ahora reside aqui.
     */
    public EstadisticasLogsDTO obtenerEstadisticasLogs() {
        long erroresHoy = logRepository.contarErroresUltimas24h();
        long erroresAyer = logRepository.contarErroresDiaAnterior();
        long totalEventos = logRepository.contarTotalEventos24h();

        EstadisticasLogsDTO maleta = new EstadisticasLogsDTO();

        // --- TARJETA 1: ERRORES REALES ---
        maleta.setTotalErrores24h(erroresHoy);
        if (erroresAyer == 0) {
            maleta.setTendenciaPorcentual(erroresHoy > 0 ? "+100% vs ayer" : "0% vs ayer");
        } else {
            long diferencia = erroresHoy - erroresAyer;
            long porcentaje = (diferencia * 100) / erroresAyer;
            maleta.setTendenciaPorcentual((diferencia > 0 ? "+" : "") + porcentaje + "% vs ayer");
        }

        // --- TARJETA 2: ACTIVIDAD REAL (SALUD) ---
        double salud = 100.00;
        if (totalEventos > 0) {
            long exitos = totalEventos - erroresHoy;
            salud = ((double) exitos / totalEventos) * 100;
        }
        maleta.setTiempoActividad(String.format("%.2f%%", salud).replace(",", "."));

        if (salud >= 99.0) {
            maleta.setEstadoActividad("Estable");
        } else if (salud >= 95.0) {
            maleta.setEstadoActividad("Aviso");
        } else {
            maleta.setEstadoActividad("Crítico");
        }

        // --- TARJETA 3: LATENCIA REAL (VELOCIDAD DISCO/RED) ---
        long inicio = System.currentTimeMillis();
        logRepository.count();
        long latenciaReal = System.currentTimeMillis() - inicio;

        maleta.setLatenciaMedia(latenciaReal + "ms");
        long latenciaBase = 30;
        long diferenciaLat = latenciaReal - latenciaBase;
        maleta.setMejoraLatencia((diferenciaLat <= 0 ? "" : "+") + diferenciaLat + "ms vs base");
        
        maleta.setUltimosLogs(logRepository.findTop50ByOrderByFechaDesc());

        return maleta;
    }

    // Metodo privado auxiliar para el grafico
    private String generarTextoGrafico() {
        long totalBandasActuales = bandaRepository.contarTodasLasBandas();
        StringBuilder constructorDatos = new StringBuilder();
        LocalDate fechaActual = LocalDate.now();

        for (int i = 5; i >= 0; i--) {
            LocalDate mesCalculado = fechaActual.minusMonths(i);
            String nombreMes = mesCalculado.getMonth()
                    .getDisplayName(TextStyle.SHORT, new Locale("es", "ES"))
                    .toUpperCase();

            long valorSimulado = Math.max(0, totalBandasActuales - (i * 2));
            constructorDatos.append(nombreMes).append(",").append(valorSimulado);

            if (i > 0) {
                constructorDatos.append(";");
            }
        }
        return constructorDatos.toString();
    }
}