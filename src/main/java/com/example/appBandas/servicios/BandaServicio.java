package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.EstadisticasDirectorioDTO;
import com.example.appBandas.modelos.Banda;
import com.example.appBandas.repositorios.BandaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio de las bandas.
 */
@Service
public class BandaServicio {

    private final BandaRepository bandaRepository;

    public BandaServicio(BandaRepository bandaRepository) {
        this.bandaRepository = bandaRepository;
    }

    public List<Banda> obtenerTodasLasBandas() {
        return bandaRepository.findAll();
    }

    public Optional<Banda> obtenerBandaPorId(Integer id) {
        return bandaRepository.findById(id);
    }
    
    public Optional<Banda> obtenerBandaPorIdentificador(String identificadorBanda) {
        return bandaRepository.findByIdentificadorBanda(identificadorBanda);
    }
    
    public long obtenerBandasPorNumero() {
        return bandaRepository.contarTodasLasBandas();
    }

    public Banda guardarBanda(Banda banda) {
        return bandaRepository.save(banda);
    }

    public void eliminarBanda(Integer id) {
        bandaRepository.deleteById(id);
    }
    
    /**
     * Calcula la diferencia de altas de bandas entre el mes actual y el anterior.
     * @return Un texto formateado indicando la subida o bajada (ej: "+5 este mes").
     */
    public String obtenerTendenciaBandas() {
        long mesActual = bandaRepository.contarBandasMesActual();
        long mesAnterior = bandaRepository.contarBandasMesAnterior();
        
        long diferencia = mesActual - mesAnterior;

        if (diferencia > 0) {
            return "+" + diferencia + " este mes";
        } else if (diferencia < 0) {
            return diferencia + " este mes";
        } else {
            return "Sin cambios este mes";
        }
    }
    
    public long obtenerTotalInactivas() {
        return bandaRepository.contarBandasInactivas();
    }
    
    public long obtenerTotalNuevas24h() {
        return bandaRepository.contarBandasNuevas24h();
    }
    
    /**
     * Agrupa todas las estadisticas de bandas en un solo objeto DTO.
     * @return El DTO con todos los datos listos para enviar a JavaFX.
     */
    public EstadisticasDirectorioDTO obtenerEstadisticasCompletas() {
        EstadisticasDirectorioDTO maleta = new EstadisticasDirectorioDTO();
        
        maleta.setTotalBandas(bandaRepository.contarBandasMesActual());
        
        maleta.setTendencia(obtenerTendenciaBandas());
        maleta.setInactivas(bandaRepository.contarBandasInactivas());
        maleta.setNuevas24h(bandaRepository.contarBandasNuevas24h());
        
        return maleta;
    }
    
    /**
     * Obtiene una lista limitada a las 5 bandas registradas mas recientemente.
     * @return Lista de las 5 ultimas bandas.
     */
    public List<Banda> obtenerTop5Recientes() {
        return bandaRepository.findTop5ByOrderByFechaRegistroDesc();
    }
}