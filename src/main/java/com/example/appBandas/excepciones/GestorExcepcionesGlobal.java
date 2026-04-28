package com.example.appBandas.excepciones;

import com.example.appBandas.servicios.LogServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase encargada de capturar cualquier error inesperado en la aplicacion.
 * Su funcion es interceptar la excepcion, extraer el origen y el mensaje,
 * registrarlo en la base de datos y devolver una respuesta amigable.
 */
@ControllerAdvice
public class GestorExcepcionesGlobal {

    private final LogServicio logServicio;

    public GestorExcepcionesGlobal(LogServicio logServicio) {
        this.logServicio = logServicio;
    }

    /**
     * Metodo que gestiona todas las excepciones de tipo Exception.
     * Extrae los datos de la traza de error de forma manual para evitar fallos de ejecucion.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGlobales(Exception e) {

        String origen = "Desconocido";
        String mensaje = "Error sin mensaje detallado";

        if (e.getMessage() != null) {
            mensaje = e.getMessage();
        }

        StackTraceElement[] trazaCompleta = e.getStackTrace();

        if (trazaCompleta != null && trazaCompleta.length > 0) {
            StackTraceElement primerElemento = trazaCompleta[0];
            origen = primerElemento.getClassName();
        }

        logServicio.registrarNuevoLog(
                "ERROR",
                origen,
                mensaje,
                "Capturado por el Gestor Global"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrio un error en el servidor. El administrador ha sido notificado.");
    }
}