package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Evento;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para gestionar las operaciones de persistencia de la entidad
 * Evento.
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

	/**
	 * Busca todos los eventos asociados a una banda específica.
	 * 
	 * @param idBanda El identificador de la banda.
	 * @return Una lista de eventos.
	 */
	List<Evento> findByBandaIdBanda(Integer idBanda);

	@Query("SELECT COUNT(DISTINCT e.banda) FROM Evento e WHERE cast(e.fHora as date) = current_date AND e.tipo LIKE '%Ensayo%'")
	Long contarEnsayosDeBandas();
	
	@Query("SELECT COUNT(e) FROM Evento e WHERE e.banda.idBanda = :idBanda AND e.tipo LIKE '%Ensayo%' AND e.fHora BETWEEN :inicio AND :fin")
    long contarEnsayosPorFechas(@Param("idBanda") Integer idBanda, @Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

	long countByBanda_IdBanda(Integer idBanda);

	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.banda.idBanda = :idBanda AND u.ultimoAcceso >= :fechaLimite")
	long contarMusicosActivos(Integer idBanda, LocalDateTime fechaLimite);
	
	List<Evento> findByBanda_IdBandaOrderByFHoraDesc(Integer idBanda);
}