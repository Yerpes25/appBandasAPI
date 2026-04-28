package com.example.appBandas.controladores;

import com.example.appBandas.servicios.LogServicio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filtro que mide el tiempo de respuesta de cada peticion HTTP.
 * Calcula la diferencia entre el inicio y el fin del procesamiento
 * y registra latencias elevadas en el sistema de logs.
 */
@Component
public class FiltroLatencia extends OncePerRequestFilter {

    private final LogServicio logServicio;

    public FiltroLatencia(LogServicio logServicio) {
        this.logServicio = logServicio;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Capturamos el tiempo de inicio en milisegundos
        long tiempoInicio = System.currentTimeMillis();

        try {
            // Continuar con la ejecucion normal de la peticion
            filterChain.doFilter(request, response);
        } finally {
            // Calculamos el tiempo transcurrido
            long tiempoFin = System.currentTimeMillis();
            long duracion = tiempoFin - tiempoInicio;

            // Solo registramos si la peticion tarda mas de 500ms para no saturar la BD
            if (duracion > 500) {
                logServicio.registrarNuevoLog(
                    "INFO",
                    "FiltroLatencia",
                    "Peticion lenta detectada en: " + request.getRequestURI(),
                    "Tiempo de respuesta: " + duracion + " ms"
                );
            }
        }
    }
}