package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Marcha;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.MarchaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio para gestionar el repertorio musical de la banda.
 */
@Service
public class MarchaServicio {

    private final MarchaRepository marchaRepository;
    private final BandaRepository bandaRepository;

    public MarchaServicio(MarchaRepository marchaRepository, BandaRepository bandaRepository) {
        this.marchaRepository = marchaRepository;
        this.bandaRepository = bandaRepository;
    }

    public List<Marcha> obtenerTodasLasMarchas() {
        return marchaRepository.findAll();
    }

    public Optional<Marcha> obtenerMarchaPorId(Integer id) {
        return marchaRepository.findById(id);
    }

    public Marcha guardarMarcha(Marcha marcha) {
        return marchaRepository.save(marcha);
    }

    public void eliminarMarcha(Integer id) {
        marchaRepository.deleteById(id);
    }
    
    public Map<String, Object> obtenerResumenMarchas(Integer idBanda) {
        Map<String, Object> maleta = new HashMap<>();
        
        // 1. Buscamos todas las marchas de la base de datos
        List<Marcha> marchasBd = marchaRepository.findByBanda_IdBanda(idBanda);
        
        // 2. Las formateamos para enviarlas limpias a JavaFX
        List<Map<String, String>> listaFormateada = new ArrayList<>();
        
        for (Marcha m : marchasBd) {
            Map<String, String> item = new HashMap<>();
            item.put("titulo", m.getNombre() != null ? m.getNombre() : "Sin título");
            item.put("compositor", m.getAutor() != null ? m.getAutor() : "Desconocido");
            
            // AHORA LEE DE LA BASE DE DATOS (Si es nulo, pone un texto por defecto)
            item.put("categoria", m.getCategoria() != null ? m.getCategoria() : "Sin categoría"); 
            item.put("estado", m.getEstado() != null ? m.getEstado() : "Sin estado"); 
            
            listaFormateada.add(item);
        }

        // 3. Metemos la lista y el total en la maleta
        maleta.put("total", listaFormateada.size());
        maleta.put("lista", listaFormateada);

        return maleta;
    }
    
    public Marcha guardarMarchaConBanda(Integer idBanda, Marcha marcha) throws Exception {
        Banda banda = bandaRepository.findById(idBanda)
                .orElseThrow(() -> new Exception("Banda no encontrada con ID: " + idBanda));
        
        marcha.setBanda(banda);
        return marchaRepository.save(marcha);
    }
}