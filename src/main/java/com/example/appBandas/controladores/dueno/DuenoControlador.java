package com.example.appBandas.controladores.dueno;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appBandas.dtos.EstadisticasDuenoInicioDTO;
import com.example.appBandas.servicios.ComponentesServicio;
import com.example.appBandas.servicios.EstadisticasDuenoServicio;

/**
 * Controlador REST encargado de atender las peticiones del perfil Dueño.
 * Delega la lógica de negocio a EstadisticasDuenoServicio y ComponentesServicio.
 */
@RestController
@RequestMapping("/api/dueno")
public class DuenoControlador {

    private final EstadisticasDuenoServicio estadisticasServicio;
    private final ComponentesServicio componentesServicio;

    // Solo inyectamos los servicios, NINGÚN repositorio
    public DuenoControlador(EstadisticasDuenoServicio estadisticasServicio, ComponentesServicio componentesServicio) {
        this.estadisticasServicio = estadisticasServicio;
        this.componentesServicio = componentesServicio;
    }

    @GetMapping("/estadisticas-inicio/{idBanda}")
    public ResponseEntity<EstadisticasDuenoInicioDTO> obtenerEstadisticasInicio(@PathVariable Integer idBanda) {
        // Llamamos al método generarMaletaDueño que ya tienes perfectamente configurado en tu Servicio
        EstadisticasDuenoInicioDTO maleta = estadisticasServicio.generarMaletaDueño(idBanda);
        return ResponseEntity.ok(maleta);
    }
    
    @GetMapping("/componentes/{idBanda}")
    public ResponseEntity<List<Map<String, String>>> obtenerComponentesBanda(@PathVariable Integer idBanda) {
        List<Map<String, String>> listaComponentes = componentesServicio.obtenerListaComponentes(idBanda);
        return ResponseEntity.ok(listaComponentes);
    }
    
    @PostMapping("/componentes/{idBanda}/nuevo")
    public ResponseEntity<String> añadirNuevoMusico(@PathVariable Integer idBanda, @RequestBody Map<String, String> datos) {
        boolean guardado = componentesServicio.crearNuevoMusico(idBanda, datos);
        
        if (guardado) {
            return ResponseEntity.ok("Músico guardado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Error al guardar el músico");
        }
    }
    
    @PutMapping("/componentes/actualizar/{idUsuario}")
    public ResponseEntity<String> actualizarMusico(@PathVariable Integer idUsuario, @RequestBody Map<String, String> datos) {
        boolean actualizado = componentesServicio.actualizarMusico(idUsuario, datos);
        if (actualizado) {
            return ResponseEntity.ok("Músico actualizado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Error al actualizar el músico");
        }
    }
}