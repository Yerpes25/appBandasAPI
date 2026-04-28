package com.example.appBandas.repositorios;

import com.example.appBandas.modelos.LogSistema;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar las consultas reales de logs.
 * Proporciona los datos exactos para las estadisticas del administrador.
 */
@Repository
public interface LogRepository extends JpaRepository<LogSistema, Long> {
    
    // Cuenta los errores reales en las ultimas 24h
    @Query(value = "SELECT COUNT(*) FROM logs_sistema WHERE nivel = 'ERROR' AND fecha >= DATE_SUB(NOW(), INTERVAL 1 DAY)", nativeQuery = true)
    long contarErroresUltimas24h();

    // Cuenta los errores de hace 2 dias para la tendencia
    @Query(value = "SELECT COUNT(*) FROM logs_sistema WHERE nivel = 'ERROR' AND fecha >= DATE_SUB(NOW(), INTERVAL 2 DAY) AND fecha < DATE_SUB(NOW(), INTERVAL 1 DAY)", nativeQuery = true)
    long contarErroresDiaAnterior();
    
    // Cuenta TODOS los eventos (INFO, WARN, ERROR) para calcular el Uptime real
    @Query(value = "SELECT COUNT(*) FROM logs_sistema WHERE fecha >= DATE_SUB(NOW(), INTERVAL 1 DAY)", nativeQuery = true)
    long contarTotalEventos24h();

    List<LogSistema> findTop50ByOrderByFechaDesc();
    
    /**
     * Obtiene el conteo de reinicios agrupados por mes.
     * Se utiliza una consulta nativa compatible con MySQL 5.5.
     */
    @Query(value = "SELECT DATE_FORMAT(fecha_creacion, '%Y-%m') as etiqueta, COUNT(*) as cantidad " +
                   "FROM logs_sistema " +
                   "WHERE nivel = 'SISTEMA' AND mensaje LIKE '%iniciado%' " +
                   "GROUP BY etiqueta ORDER BY etiqueta DESC", nativeQuery = true)
    List<Object[]> obtenerEstadisticasReinicios();
    
 // Obtiene los textos de latencia de las ultimas 24h
    @Query(value = "SELECT contexto FROM logs_sistema WHERE origen = 'FiltroLatencia' AND fecha >= DATE_SUB(NOW(), INTERVAL 1 DAY)", nativeQuery = true)
    List<String> obtenerLatenciasUltimas24h();

    // Obtiene los textos de latencia de hace 2 dias para comparar
    @Query(value = "SELECT contexto FROM logs_sistema WHERE origen = 'FiltroLatencia' AND fecha >= DATE_SUB(NOW(), INTERVAL 2 DAY) AND fecha < DATE_SUB(NOW(), INTERVAL 1 DAY)", nativeQuery = true)
    List<String> obtenerLatenciasDiaAnterior();
}

