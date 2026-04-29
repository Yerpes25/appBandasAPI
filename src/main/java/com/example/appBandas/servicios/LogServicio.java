package com.example.appBandas.servicios;

import com.example.appBandas.modelos.LogSistema;
import com.example.appBandas.repositorios.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public void registrarNuevoLog(String nivel, String origen, String mensaje, String contexto) {
        LogSistema nuevoLog = new LogSistema();
        nuevoLog.setFecha(LocalDateTime.now());
        nuevoLog.setNivel(nivel);
        
        // PROTECCIÓN MANUAL: Recortamos el origen a 50 caracteres máximos para que no explote MySQL
        nuevoLog.setOrigen(origen != null && origen.length() > 50 ? origen.substring(0, 50) : origen);
        
        // Recortamos el mensaje a 900 caracteres por si acaso es un error gigante para que no rompa la BD
        nuevoLog.setMensaje(mensaje != null && mensaje.length() > 900 ? mensaje.substring(0, 900) + "..." : mensaje);
        nuevoLog.setContexto(contexto);
        
        logRepositorio.save(nuevoLog);
    }
    
    /**
     * Recupera las estadisticas de reinicios del sistema agrupadas por meses.
     * Transforma la lista de arreglos de objetos en un mapa facil de leer.
     */
    public Map<String, Long> consultarReiniciosMensuales() {

        List<Object[]> resultados = logRepositorio.obtenerEstadisticasReinicios();

        Map<String, Long> estadisticas = new LinkedHashMap<>();

        for (Object[] fila : resultados) {

            String mes = null;
            Long cantidad = 0L;

            if (fila != null && fila.length >= 2) {

                if (fila[0] != null) {
                    mes = fila[0].toString();
                }

                if (fila[1] != null) {
                    Number valorNumerico = (Number) fila[1];
                    cantidad = valorNumerico.longValue();
                }
            }

            if (mes != null) {
                estadisticas.put(mes, cantidad);
            }
        }

        return estadisticas;
    }
    
    /**
     * Elimina un registro del sistema permanentemente usando su identificador.
     */
    public void eliminarLog(Long idLog) {
        if (logRepositorio.existsById(idLog)) {
            logRepositorio.deleteById(idLog);
        }
    }
    
 // Calcula la media de latencia extrayendo el numero del texto del log
    public long calcularLatenciaMedia(List<String> contextosLatencia) {
        if (contextosLatencia == null || contextosLatencia.isEmpty()) {
            return 0;
        }

        long sumaTotal = 0;
        int validos = 0;

        for (String contexto : contextosLatencia) {
            try {
                // Limpiamos el texto para quedarnos solo con el numero
                String numeroPuro = contexto.replace("Tiempo de respuesta: ", "").replace(" ms", "").trim();
                sumaTotal += Long.parseLong(numeroPuro);
                validos++;
            } catch (NumberFormatException e) {
                // Ignoramos si un texto vino corrupto
            }
        }

        return validos > 0 ? (sumaTotal / validos) : 0;
    }

    public List<String> obtenerLatenciasHoy() {
        return logRepositorio.obtenerLatenciasUltimas24h();
    }

    public List<String> obtenerLatenciasAyer() {
        return logRepositorio.obtenerLatenciasDiaAnterior();
    }
}