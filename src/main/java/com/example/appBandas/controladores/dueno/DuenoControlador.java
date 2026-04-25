package com.example.appBandas.controladores.dueno;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appBandas.dtos.EstadisticasDuenoInicioDTO;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.TransaccionRepository;
import com.example.appBandas.repositorios.UsuarioRepository;
import com.example.appBandas.servicios.ComponentesServicio;
import com.example.appBandas.servicios.EstadisticasDuenoServicio;

@RestController
@RequestMapping("/api/dueno")
public class DuenoControlador {

    private final EstadisticasDuenoServicio estadisticasServicio;
    private final ComponentesServicio componentesServicio;
    private final TransaccionRepository transaccionRepo;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepo;

	public DuenoControlador(EstadisticasDuenoServicio estadisticasServicio, ComponentesServicio componentesServicio,
			TransaccionRepository transaccionRepo, EventoRepository eventoRepository, UsuarioRepository usuarioRepo) {
		super();
		this.estadisticasServicio = estadisticasServicio;
		this.componentesServicio = componentesServicio;
		this.transaccionRepo = transaccionRepo;
		this.eventoRepository = eventoRepository;
		this.usuarioRepo = usuarioRepo;
	}

	@GetMapping("/estadisticas-inicio/{idBanda}")
    public Map<String, Object> obtenerEstadisticasInicio(@PathVariable Integer idBanda) {
        Map<String, Object> maleta = new HashMap<>();
        LocalDate hoy = LocalDate.now();

        // --- CÁLCULO DE ENSAYOS (EVENTOS TIPO ENSAYO) ---
        
        // 1. Límites de la semana actual (Lunes 00:00 a Domingo 23:59)
        LocalDateTime inicioSemana = hoy.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime finSemana = hoy.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(LocalTime.MAX);
        
        // 2. Límites de la semana pasada
        LocalDateTime inicioPasada = inicioSemana.minusWeeks(1);
        LocalDateTime finPasada = finSemana.minusWeeks(1);

        // 3. Consultas al repositorio usando contarEnsayosPorFechas 
        long ensayosEstaSemana = eventoRepository.contarEnsayosPorFechas(idBanda, inicioSemana, finSemana);
        long ensayosSemanaPasada = eventoRepository.contarEnsayosPorFechas(idBanda, inicioPasada, finPasada);

        // 4. Calcular diferencia para la comparativa
        long diferencia = ensayosEstaSemana - ensayosSemanaPasada;
        String comparativa = (diferencia >= 0 ? "+" : "") + diferencia;

        // --- RESTO DE DATOS ---
        
        // Músicos
        long total = usuarioRepo.countByBanda_IdBanda(idBanda);
        
        // Tesorería (Últimos 3 meses)
        java.time.LocalDate haceTresMeses = hoy.minusMonths(3);
        Double ingresos = transaccionRepo.sumarIngresosPorPeriodo(idBanda, haceTresMeses);

        // Rellenar maleta de respuesta
        maleta.put("totalMusicos", total);
        maleta.put("musicosActivos", total); 
        maleta.put("totalEnsayos", ensayosEstaSemana);
        maleta.put("ensayosComparativa", comparativa);
        maleta.put("ingresosTrimestral", ingresos != null ? ingresos : 0.0);
        maleta.put("porcentajeIngresos", "+0.0"); 
        maleta.put("estadoIngresos", "AL DÍA");
        
        // Listas (Simuladas o de otros repositorios)
        maleta.put("marchasNuevas", new ArrayList<>()); 
        maleta.put("inventarioEscaso", new ArrayList<>()); 

        return maleta;
    }

	// Métodos auxiliares para que el Dashboard no se vea vacío mientras creas los otros repositorios
	private List<Map<String, String>> obtenerMarchasSimuladas() {
	    List<Map<String, String>> marchas = new ArrayList<>();
	    // Ejemplo de cómo enviarlo para que tu procesarMarchas() de JavaFX lo lea bien
	    Map<String, String> m1 = new HashMap<>();
	    m1.put("titulo", "Pasan los Campanilleros");
	    m1.put("autor", "M. López Farfán");
	    m1.put("tiempo", "4:30");
	    marchas.add(m1);
	    return marchas;
	}

	private List<Map<String, String>> obtenerInventarioSimulado() {
	    List<Map<String, String>> inv = new ArrayList<>();
	    Map<String, String> i1 = new HashMap<>();
	    i1.put("nombre", "Cañas Clarinete 2.5");
	    i1.put("stock", "3");
	    inv.add(i1);
	    return inv;
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
}