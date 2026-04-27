package com.example.appBandas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Asistencia;
import com.example.appBandas.modelos.AsistenciaId;

/**
 * Repositorio para gestionar la asistencia. Al usar clave compuesta, se indica
 * AsistenciaId como el tipo de ID.
 */
@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, AsistenciaId> {    
	// Cuenta CUÁNTOS ENSAYOS totales se han convocado para este usuario
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.evento.tipo = 'Ensayo'")
    long contarEnsayosTotalesDelUsuario(@Param("idUsuario") Integer idUsuario);

    // Cuenta A CUÁNTOS ENSAYOS ha asistido realmente
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.evento.tipo = 'Ensayo' AND a.estado = 'Asiste'")
    long contarEnsayosAsistidosPositivos(@Param("idUsuario") Integer idUsuario);
    
 // Cuenta todos los eventos que NO son ensayos
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.evento.tipo <> 'Ensayo'")
    long contarOtrosEventosTotales(@Param("idUsuario") Integer idUsuario);

    // Cuenta asistencias positivas a eventos que NO son ensayos
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.evento.tipo <> 'Ensayo' AND a.estado = 'Asiste'")
    long contarOtrosEventosAsistidosPositivos(@Param("idUsuario") Integer idUsuario);
    
 // Cuenta TODOS los eventos a los que ha sido convocado (Ensayos + Conciertos)
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario")
    long contarEventosTotalesDelUsuario(@Param("idUsuario") Integer idUsuario);

    // Cuenta TODAS las asistencias reales globales
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.estado = 'Asiste'")
    long contarAsistenciasPositivas(@Param("idUsuario") Integer idUsuario);
    
    List<Asistencia> findByUsuario_IdUsuario(Integer idUsuario);
}