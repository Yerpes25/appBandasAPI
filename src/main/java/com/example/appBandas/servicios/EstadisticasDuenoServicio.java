package com.example.appBandas.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.EstadisticasDuenoInicioDTO;
import com.example.appBandas.modelos.Inventario;
import com.example.appBandas.modelos.Marcha;
import com.example.appBandas.repositorios.ContratoRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.InventarioRepository;
import com.example.appBandas.repositorios.MarchaRepository;
import com.example.appBandas.repositorios.TransaccionRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

@Service
public class EstadisticasDuenoServicio {

    private final UsuarioRepository usuarioRepo;
    private final EventoRepository eventoRepo;
    private final ContratoRepository contratoRepo;
    private final TransaccionRepository transaccionRepo;
    private final InventarioRepository inventarioRepo;
    private final MarchaRepository marchaRepo;

    public EstadisticasDuenoServicio(UsuarioRepository usuarioRepo, EventoRepository eventoRepo,
			ContratoRepository contratoRepo, TransaccionRepository transaccionRepo, InventarioRepository inventarioRepo,
			MarchaRepository marchaRepo) {
		super();
		this.usuarioRepo = usuarioRepo;
		this.eventoRepo = eventoRepo;
		this.contratoRepo = contratoRepo;
		this.transaccionRepo = transaccionRepo;
		this.inventarioRepo = inventarioRepo;
		this.marchaRepo = marchaRepo;
	}

	public EstadisticasDuenoInicioDTO generarMaletaDueño(Integer idBanda) {
        EstadisticasDuenoInicioDTO maleta = new EstadisticasDuenoInicioDTO();

        // ---------------------------------------------------------
        // 1. TARJETA MÚSICOS
        // ---------------------------------------------------------
        maleta.setTotalMusicos(usuarioRepo.countByBanda_IdBanda(idBanda));
        
        // Músicos que se han conectado en las últimas 24 horas (1 día)
        LocalDateTime haceUnDia = LocalDateTime.now().minusDays(1);
        maleta.setMusicosActivos(usuarioRepo.contarMusicosActivos(idBanda, haceUnDia));

        // ---------------------------------------------------------
        // 2. TARJETA ENSAYOS (Comparativa Lunes a Domingo)
        // ---------------------------------------------------------
        LocalDateTime ahora = LocalDateTime.now();
        
        // Calculamos el Lunes a las 00:00 y el Domingo a las 23:59 de ESTA semana
        LocalDateTime inicioEstaSemana = ahora.with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
        LocalDateTime finEstaSemana = ahora.with(java.time.DayOfWeek.SUNDAY).toLocalDate().atTime(23, 59, 59);

        // Calculamos el Lunes y el Domingo de la semana PASADA
        LocalDateTime inicioSemanaPasada = inicioEstaSemana.minusWeeks(1);
        LocalDateTime finSemanaPasada = finEstaSemana.minusWeeks(1);

        // Contamos los ensayos en esos rangos exactos de fechas
        long ensayosEstaSemana = eventoRepo.contarEnsayosPorFechas(idBanda, inicioEstaSemana, finEstaSemana);
        long ensayosSemanaPasada = eventoRepo.contarEnsayosPorFechas(idBanda, inicioSemanaPasada, finSemanaPasada);
        
        maleta.setTotalEnsayos(ensayosEstaSemana);
        
        long diferenciaEnsayos = ensayosEstaSemana - ensayosSemanaPasada;
        if (diferenciaEnsayos > 0) {
            maleta.setEnsayosComparativa("+" + diferenciaEnsayos);
        } else if (diferenciaEnsayos < 0) {
            maleta.setEnsayosComparativa(String.valueOf(diferenciaEnsayos)); // Ya lleva el menos negativo
        } else {
            maleta.setEnsayosComparativa("0");
        }

        // ---------------------------------------------------------
        // 3. TARJETA INGRESOS (Comparativa de Trimestres - 3 meses)
        // ---------------------------------------------------------
        LocalDate hoyFecha = LocalDate.now();
        int mesActual = hoyFecha.getMonthValue();
        int mesInicioTrimestre = ((mesActual - 1) / 3) * 3 + 1;
        LocalDate inicioTrimestre = LocalDate.of(hoyFecha.getYear(), mesInicioTrimestre, 1);

        Double ingresosEsteTrimestre = transaccionRepo.sumarIngresosRecientes(idBanda, inicioTrimestre);

        maleta.setIngresosTrimestral(ingresosEsteTrimestre != null ? ingresosEsteTrimestre : 0.0);
        
        maleta.setPorcentajeIngresos("");
        maleta.setEstadoIngresos("");

     // ---------------------------------------------------------
        // 4. TABLAS (Convertimos los objetos en Listas de Textos simples)
        // ---------------------------------------------------------
        
        // Tabla de Marchas (Pedimos todas en vez de 5 para que el frontend pagine)
        List<Marcha> marchasNuevasBD = marchaRepo.findByBanda_IdBandaOrderByFMontajeDesc(idBanda);
        List<Map<String, String>> listaMarchasJSON = new ArrayList<>();
        
        for (Marcha m : marchasNuevasBD) {
            Map<String, String> fila = new HashMap<>();
            fila.put("titulo", m.getNombre() != null ? m.getNombre() : "Sin título");
            fila.put("autor", m.getAutor() != null ? m.getAutor() : "Desconocido");
            fila.put("tiempo", m.getTiempo() != null ? m.getTiempo() : "0:00");
            listaMarchasJSON.add(fila);
        }
        maleta.setMarchasNuevas(listaMarchasJSON);

        // Tabla de Inventario Escaso (Stock menor a 5)
        List<Inventario> inventarioEscasoBD = inventarioRepo.findByBanda_IdBandaAndStockLessThan(idBanda, 5);
        List<Map<String, String>> listaInventarioJSON = new ArrayList<>();
        
        for (Inventario inv : inventarioEscasoBD) {
            Map<String, String> fila = new HashMap<>();
            fila.put("nombre", inv.getNombre() != null ? inv.getNombre() : inv.getTipo()); // Por si "nombre" está vacío, usa "tipo"
            fila.put("stock", String.valueOf(inv.getStock() != null ? inv.getStock() : 0));
            listaInventarioJSON.add(fila);
        }
        maleta.setInventarioEscaso(listaInventarioJSON);

        return maleta;
    }
}