package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Asistencia;
import com.example.appBandas.modelos.AsistenciaId;
import com.example.appBandas.repositorios.AsistenciaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las confirmaciones de asistencia a los eventos.
 * Permite registrar faltas, justificaciones y será la base para el cálculo de estadísticas.
 */
@Service
public class AsistenciaServicio {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServicio(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> obtenerAsistencia(Integer idUsuario, Integer idEvento) {
        AsistenciaId id = new AsistenciaId(idUsuario, idEvento);
        return asistenciaRepository.findById(id);
    }

    public Asistencia guardarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public void eliminarAsistencia(Integer idUsuario, Integer idEvento) {
        AsistenciaId id = new AsistenciaId(idUsuario, idEvento);
        asistenciaRepository.deleteById(id);
    }

    /**
     * Lógica para calcular el porcentaje de asistencia de un usuario.
     * (Requiere añadir un método 'findByUsuarioIdUsuario' en AsistenciaRepository más adelante).
     */
    public double calcularPorcentajeAsistencia(List<Asistencia> asistenciasDelUsuario, int totalEventos) {
        if (totalEventos == 0) return 0.0;
        
        long asistenciasConfirmadas = asistenciasDelUsuario.stream()
                .filter(a -> "Asiste".equalsIgnoreCase(a.getEstado()))
                .count();
                
        return ((double) asistenciasConfirmadas / totalEventos) * 100;
    }
}