package com.example.appBandas.servicios;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Transaccion;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio encargado de gestionar la logica de negocio de la tesoreria.
 * Realiza los calculos de balances, ingresos y gastos, y gestiona el guardado
 * de nuevas transacciones asociadas a una banda.
 */
@Service
public class TesoreriaServicio {

    private final TransaccionRepository transaccionRepositorio;
    private final BandaRepository bandaRepositorio;

    public TesoreriaServicio(TransaccionRepository transaccionRepositorio, BandaRepository bandaRepositorio) {
        this.transaccionRepositorio = transaccionRepositorio;
        this.bandaRepositorio = bandaRepositorio;
    }

    public Map<String, Object> obtenerResumenTesoreria(Integer idBanda) {
        Map<String, Object> respuesta = new HashMap<>();
        LocalDate haceUnMes = LocalDate.now().minusDays(30);

        Double balance = transaccionRepositorio.obtenerBalanceTotal(idBanda);
        Double ingresos = transaccionRepositorio.sumarIngresosRecientes(idBanda, haceUnMes);
        Double gastos = transaccionRepositorio.sumarGastosRecientes(idBanda, haceUnMes);

        respuesta.put("balanceTotal", balance != null ? balance : 0.0);
        respuesta.put("ingresosRecientes", ingresos != null ? ingresos : 0.0);
        respuesta.put("gastosRecientes", Math.abs(gastos != null ? gastos : 0.0));
        respuesta.put("tendencia", "+15.2"); 
        
        List<Transaccion> historial = transaccionRepositorio.findByBanda_IdBandaOrderByFechaDesc(idBanda);
        respuesta.put("historial", historial);

        return respuesta;
    }

    public Transaccion guardarNuevaTransaccion(Integer idBanda, Transaccion transaccion) {
        Banda bandaAsociada = bandaRepositorio.findById(idBanda)
                .orElseThrow(() -> new RuntimeException("Banda no encontrada"));

        transaccion.setBanda(bandaAsociada);
        if (transaccion.getFecha() == null) {
            transaccion.setFecha(LocalDate.now());
        }

        return transaccionRepositorio.save(transaccion);
    }
}