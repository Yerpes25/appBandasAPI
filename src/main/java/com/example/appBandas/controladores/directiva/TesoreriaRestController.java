package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Transaccion;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.TransaccionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Controlador para exponer los datos de tesorería a la aplicación JavaFX.
 */
@RestController
@RequestMapping("/api/tesoreria")
public class TesoreriaRestController {

    private final TransaccionRepository transaccionRepo;
    private final BandaRepository bandaRepository;

    public TesoreriaRestController(TransaccionRepository transaccionRepo, BandaRepository bandaRepository) {
		super();
		this.transaccionRepo = transaccionRepo;
		this.bandaRepository = bandaRepository;
	}

	@GetMapping("/{idBanda}")
    public Map<String, Object> obtenerResumenTesoreria(@PathVariable Integer idBanda) {
        Map<String, Object> respuesta = new HashMap<>();
        LocalDate haceUnMes = LocalDate.now().minusDays(30);

        // Calculamos los datos para las tarjetas superiores
        Double balance = transaccionRepo.obtenerBalanceTotal(idBanda);
        Double ingresos = transaccionRepo.sumarIngresosRecientes(idBanda, haceUnMes);
        Double gastos = transaccionRepo.sumarGastosRecientes(idBanda, haceUnMes);

        respuesta.put("balanceTotal", balance != null ? balance : 0.0);
        respuesta.put("ingresosRecientes", ingresos != null ? ingresos : 0.0);
        respuesta.put("gastosRecientes", Math.abs(gastos != null ? gastos : 0.0));
        respuesta.put("tendencia", "+15.2"); // Esto podrías calcularlo comparando con el mes anterior
        
        // Enviamos el historial para la tabla
        List<Transaccion> historial = transaccionRepo.findByBanda_IdBandaOrderByFechaDesc(idBanda);
        respuesta.put("historial", historial);

        return respuesta;
    }

    @PostMapping("/{idBanda}/nueva")
    public ResponseEntity<?> guardarNueva(@PathVariable Integer idBanda, @RequestBody Transaccion t) {
        try {
            // 1. Buscamos la banda en la BD para que el objeto no sea null
            Banda bandaAsociada = bandaRepository.findById(idBanda).orElse(null);
            if (bandaAsociada == null) return ResponseEntity.badRequest().body("Banda no encontrada");

            // 2. Le asignamos la banda y la fecha de hoy
            t.setBanda(bandaAsociada);
            if (t.getFecha() == null) t.setFecha(LocalDate.now());

            // 3. Guardamos
            transaccionRepo.save(t);
            return ResponseEntity.ok("Guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}