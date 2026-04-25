package com.example.appBandas.servicios;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.EstadisticasInicioDTO;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

@Service
public class AdminServicio {

    private final BandaRepository bandaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    // Inyectamos los 3 repositorios para poder coger datos de todos lados
    public AdminServicio(BandaRepository bandaRepository, UsuarioRepository usuarioRepository, EventoRepository eventoRepository) {
        this.bandaRepository = bandaRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    /**
     * Llena la Super-Maleta con todos los datos del Dashboard Principal.
     */
    public EstadisticasInicioDTO obtenerEstadisticasInicio() {
        EstadisticasInicioDTO maleta = new EstadisticasInicioDTO();
        
        // 1. Datos simples
        maleta.setTotalBandas(bandaRepository.contarTodasLasBandas());
        maleta.setMusicosTotales(usuarioRepository.contarTodosLosUsuarios());
        maleta.setEnsayosHoy(eventoRepository.contarEnsayosDeBandas());
        maleta.setUltimasBandas(bandaRepository.findTop5ByOrderByFechaRegistroDesc());
        
        // 2. Carga del servidor
        Runtime entorno = Runtime.getRuntime();
        long memoriaTotal = entorno.totalMemory();
        long memoriaUsada = memoriaTotal - entorno.freeMemory();
        maleta.setCargaServidor((int) ((memoriaUsada * 100) / memoriaTotal));
        
        // 3. Generar el grafico
        maleta.setDatosGrafico(generarTextoGrafico());
        
        return maleta;
    }

    // Metodo privado auxiliar para el grafico (lo que tenias en el controlador)
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