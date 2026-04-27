package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.EstadisticasAsistenciaDTO;
import com.example.appBandas.modelos.Asistencia;
import com.example.appBandas.modelos.AsistenciaId;
import com.example.appBandas.modelos.Evento;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.AsistenciaRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las confirmaciones de asistencia a los eventos.
 * Permite registrar faltas, justificaciones y será la base para el cálculo de estadísticas.
 */
@Service
public class AsistenciaServicio {

    private final AsistenciaRepository asistenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public AsistenciaServicio(AsistenciaRepository asistenciaRepository, UsuarioRepository usuarioRepository,
			EventoRepository eventoRepository) {
		super();
		this.asistenciaRepository = asistenciaRepository;
		this.usuarioRepository = usuarioRepository;
		this.eventoRepository = eventoRepository;
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
    
    /*
     * Metodo que registra o actualiza el voto de un componente para un evento.
     * Busca al usuario y al evento; si existen, crea o actualiza la asistencia.
     */
    public Asistencia registrarVoto(Integer idUsuario, Integer idEvento, String estado, String observacion) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new Exception("Evento no encontrado"));

        AsistenciaId idCompuesto = new AsistenciaId(idUsuario, idEvento);
        
        // Buscamos si ya habia votado para sobreescribirlo, o creamos uno nuevo
        Asistencia asistencia = asistenciaRepository.findById(idCompuesto).orElse(new Asistencia());
        asistencia.setId(idCompuesto);
        asistencia.setUsuario(usuario);
        asistencia.setEvento(evento);
        asistencia.setEstado(estado);
        asistencia.setObservacion(observacion);

        return asistenciaRepository.save(asistencia);
    }
    
    public List<Asistencia> obtenerAsistenciasPorUsuario(Integer idUsuario) {
        return asistenciaRepository.findByUsuario_IdUsuario(idUsuario);
    }
    
    public EstadisticasAsistenciaDTO obtenerEstadisticasUsuario(Integer idUsuario) {
        // Ahora solo contamos ensayos
        long totales = asistenciaRepository.contarEnsayosTotalesDelUsuario(idUsuario);
        long asistidos = asistenciaRepository.contarEnsayosAsistidosPositivos(idUsuario);
        
        int porcentaje = 0;
        if (totales > 0) {
            porcentaje = (int) ((asistidos * 100) / totales);
        }

        EstadisticasAsistenciaDTO dto = new EstadisticasAsistenciaDTO();
        dto.setTotales(totales);
        dto.setAsistidos(asistidos);
        dto.setPorcentaje(porcentaje);
        
        return dto;
    }
    
    public EstadisticasAsistenciaDTO obtenerEstadisticasOtrosEventos(Integer idUsuario) {
        long totales = asistenciaRepository.contarOtrosEventosTotales(idUsuario);
        long asistidos = asistenciaRepository.contarOtrosEventosAsistidosPositivos(idUsuario);
        
        int porcentaje = (totales > 0) ? (int) ((asistidos * 100) / totales) : 0;

        EstadisticasAsistenciaDTO dto = new EstadisticasAsistenciaDTO();
        dto.setTotales(totales);
        dto.setAsistidos(asistidos);
        dto.setPorcentaje(porcentaje);
        
        return dto;
    }
}