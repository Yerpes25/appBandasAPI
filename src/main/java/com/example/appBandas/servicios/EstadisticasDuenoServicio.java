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
import com.example.appBandas.repositorios.UsuarioRepository;

@Service
public class EstadisticasDuenoServicio {

    private final UsuarioRepository usuarioRepo;
    private final EventoRepository eventoRepo;
    private final ContratoRepository contratoRepo;
    private final InventarioRepository inventarioRepo;
    private final MarchaRepository marchaRepo;

    public EstadisticasDuenoServicio(UsuarioRepository usuarioRepo, EventoRepository eventoRepo,
            ContratoRepository contratoRepo, InventarioRepository inventarioRepo, MarchaRepository marchaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.eventoRepo = eventoRepo;
        this.contratoRepo = contratoRepo;
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
        // 2. TARJETA ENSAYOS (Comparativa de 7 días)
        // ---------------------------------------------------------
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime hace7Dias = hoy.minusDays(7);
        LocalDateTime hace14Dias = hoy.minusDays(14);

        long ensayosEstaSemana = eventoRepo.contarEnsayosDeBandas();
        long ensayosSemanaPasada = eventoRepo.contarEnsayosPorFechas(idBanda, hace14Dias, hace7Dias);
        
        maleta.setTotalEnsayos(ensayosEstaSemana);
        
        long diferenciaEnsayos = ensayosEstaSemana - ensayosSemanaPasada;
        if (diferenciaEnsayos > 0) {
            maleta.setEnsayosComparativa("+" + diferenciaEnsayos);
        } else if (diferenciaEnsayos < 0) {
            maleta.setEnsayosComparativa(String.valueOf(diferenciaEnsayos)); // Ya lleva el menos
        } else {
            maleta.setEnsayosComparativa("0");
        }

        // ---------------------------------------------------------
        // 3. TARJETA INGRESOS (Comparativa de Trimestres - 3 meses)
        // ---------------------------------------------------------
        LocalDate hoyFecha = LocalDate.now();
        LocalDate hace3Meses = hoyFecha.minusMonths(3);
        LocalDate hace6Meses = hoyFecha.minusMonths(6);

        double ingresosEsteTrimestre = contratoRepo.sumarIngresosPorFechas(idBanda, hace3Meses, hoyFecha);
        double ingresosTrimestrePasado = contratoRepo.sumarIngresosPorFechas(idBanda, hace6Meses, hace3Meses);

        maleta.setIngresosTrimestral(ingresosEsteTrimestre);

        if (ingresosTrimestrePasado == 0) {
            maleta.setPorcentajeIngresos(ingresosEsteTrimestre > 0 ? "+100" : "0");
            maleta.setEstadoIngresos(ingresosEsteTrimestre > 0 ? "EXCELENTE" : "ESTABLE");
        } else {
            double diferenciaDinero = ingresosEsteTrimestre - ingresosTrimestrePasado;
            long porcentaje = Math.round((diferenciaDinero / ingresosTrimestrePasado) * 100);
            
            maleta.setPorcentajeIngresos((porcentaje > 0 ? "+" : "") + porcentaje);
            
            if (porcentaje >= 10) maleta.setEstadoIngresos("EXCELENTE");
            else if (porcentaje >= 0) maleta.setEstadoIngresos("ESTABLE");
            else maleta.setEstadoIngresos("ALERTA");
        }

        // ---------------------------------------------------------
        // 4. TABLAS (Convertimos los objetos en Listas de Textos simples)
        // ---------------------------------------------------------
        
        // Tabla de Marchas (Top 5 más nuevas)
        List<Marcha> marchasNuevasBD = marchaRepo.findTop5ByBanda_IdBandaOrderByFMontajeDesc(idBanda);
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