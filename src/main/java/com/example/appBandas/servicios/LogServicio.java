package com.example.appBandas.servicios;

import com.example.appBandas.modelos.LogSistema;
import com.example.appBandas.repositorios.LogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio encargado de gestionar la logica de negocio de los registros del sistema.
 * Actua como intermediario entre el controlador y el repositorio de la base de datos,
 * proporcionando metodos limpios para obtener estadisticas y guardar nuevos eventos.
 */
@Service
public class LogServicio {

    private final LogRepository logRepositorio;

    // Inyeccion de dependencias manual mediante constructor
    public LogServicio(LogRepository logRepositorio) {
        this.logRepositorio = logRepositorio;
    }

    // Obtiene los ultimos 50 registros ordenados por fecha descendente
    public List<LogSistema> obtenerLogsRecientes() {
        return logRepositorio.findTop50ByOrderByFechaDesc();
    }

    // Cuenta la cantidad de errores ocurridos en las ultimas 24 horas
    public long contarErroresUltimas24h() {
        return logRepositorio.contarErroresUltimas24h();
    }

    // Cuenta la cantidad de errores ocurridos en el dia anterior para realizar comparaciones
    public long contarErroresDiaAnterior() {
        return logRepositorio.contarErroresDiaAnterior();
    }

    // Cuenta todos los eventos (INFO, WARN, ERROR) de las ultimas 24 horas
    public long contarTotalEventos24h() {
        return logRepositorio.contarTotalEventos24h();
    }

    // Metodo utilitario para registrar un nuevo log facilmente desde otros servicios
    public LogSistema registrarNuevoLog(String nivel, String origen, String mensaje, String contexto) {
        LogSistema nuevoLog = new LogSistema(nivel, origen, mensaje, contexto);
        return logRepositorio.save(nuevoLog);
    }
}