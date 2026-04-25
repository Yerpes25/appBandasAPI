package com.example.appBandas.repositorios;

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
	// Cuenta a cuántos eventos ha sido convocado el usuario
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario")
    long contarEventosTotalesDelUsuario(@Param("idUsuario") Integer idUsuario);

    // Cuenta a cuántos ha asistido realmente
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.usuario.idUsuario = :idUsuario AND a.estado = 'Asiste'")
    long contarAsistenciasPositivas(@Param("idUsuario") Integer idUsuario);
}