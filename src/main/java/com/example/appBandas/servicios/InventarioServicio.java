package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Inventario;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.InventarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio encargado de gestionar los activos de la banda.
 * Útil para la posterior lectura e identificación mediante códigos QR.
 */
@Service
public class InventarioServicio {

    private final InventarioRepository inventarioRepository;
    private final BandaRepository bandaRepository;

    public InventarioServicio(InventarioRepository inventarioRepository, BandaRepository bandaRepository) {
		super();
		this.inventarioRepository = inventarioRepository;
		this.bandaRepository = bandaRepository;
	}

	public List<Inventario> obtenerTodoElInventario() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> obtenerArticuloPorId(Integer id) {
        return inventarioRepository.findById(id);
    }

    public Inventario guardarArticulo(Inventario articulo) {
        return inventarioRepository.save(articulo);
    }

    public void eliminarArticulo(Integer id) {
        inventarioRepository.deleteById(id);
    }
    
    /**
     * Prepara una maleta de datos con los totales de STOCK y la lista completa de artículos.
     */
    public Map<String, Object> obtenerResumenInventario(Integer idBanda) {
        Map<String, Object> resumen = new HashMap<>();

        // Usamos los nuevos métodos de SUMA
        resumen.put("totalActivos", inventarioRepository.sumarStockTotal(idBanda));
        resumen.put("totalInstrumentos", inventarioRepository.sumarStockPorTipo(idBanda, "Instrumento"));
        resumen.put("totalUniformes", inventarioRepository.sumarStockPorTipo(idBanda, "Uniforme"));
        resumen.put("totalMantenimiento", inventarioRepository.sumarStockPorEstado(idBanda, "Mantenimiento"));
        
        // Enviamos todos los productos para la tabla
        resumen.put("productos", inventarioRepository.findByBanda_IdBanda(idBanda));

        return resumen;
	}
    
    public Inventario guardarArticuloConBanda(Integer idBanda, Inventario articulo) throws Exception {
        // 1. Buscamos la banda
        Banda banda = bandaRepository.findById(idBanda)
                .orElseThrow(() -> new Exception("Banda no encontrada con ID: " + idBanda));
        
        // 2. Se la asignamos al artículo
        articulo.setBanda(banda);
        
        // 3. Guardamos y devolvemos
        return inventarioRepository.save(articulo);
    }
}