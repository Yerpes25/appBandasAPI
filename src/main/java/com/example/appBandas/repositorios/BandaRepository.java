package com.example.appBandas.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Banda;

/**
 * Repositorio para gestionar las operaciones de persistencia de la entidad
 * Banda.
 */
@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {
	// Nueva linea para que busque una banda usando su CIF
    Optional<Banda> findByIdentificadorBanda(String identificadorBanda);
    
    /**
     * Consulta manual para contar cuantas bandas existen en total.
     * @return El numero total de bandas.
     */
    @Query("SELECT COUNT(b) FROM Banda b")
    long contarTodasLasBandas();
    
    /**
     * Cuenta cuantas bandas se han registrado en la app en el mes y ano actual.
     * @return El numero de bandas de este mes.
     */
    @Query(value = "SELECT COUNT(*) FROM banda WHERE MONTH(fecha_registro) = MONTH(CURRENT_DATE()) AND YEAR(fecha_registro) = YEAR(CURRENT_DATE())", nativeQuery = true)
    long contarBandasMesActual();
    
    /**
     * Cuenta cuantas bandas se registraron en la app exactamente el mes pasado.
     * @return El numero de bandas del mes pasado.
     */
    @Query(value = "SELECT COUNT(*) FROM banda WHERE MONTH(fecha_registro) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(fecha_registro) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH)", nativeQuery = true)
    long contarBandasMesAnterior();
    
    /**
     * Cuenta el numero de bandas cuya ultima conexion fue hace mas de 6 meses.
     * Utiliza la funcion DATE_SUB de MySQL para restar meses a la fecha actual.
     * @return El numero total de bandas inactivas.
     */
    @Query(value = "SELECT COUNT(*) FROM banda WHERE ultima_conexion < DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH)", nativeQuery = true)
    long contarBandasInactivas();
    
    /**
     * Cuenta el numero de bandas registradas en la app en las ultimas 24 horas.
     * @return El total de bandas nuevas.
     */
    @Query(value = "SELECT COUNT(*) FROM banda WHERE fecha_registro >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)", nativeQuery = true)
    long contarBandasNuevas24h();
    
    /**
     * Devuelve las ultimas 5 bandas registradas en el sistema, ordenadas de mas nueva a mas vieja.
     */
    List<Banda> findTop5ByOrderByFechaRegistroDesc();
}