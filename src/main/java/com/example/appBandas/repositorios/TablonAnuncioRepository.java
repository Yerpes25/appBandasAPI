package com.example.appBandas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.TablonAnuncio;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * TablonAnuncio.
 */
@Repository
public interface TablonAnuncioRepository extends JpaRepository<TablonAnuncio, Integer> {
	List<TablonAnuncio> findByBanda_IdBanda(Integer idBanda);
	
	/**
	 * Busca anuncios de una banda que no hayan caducado.
	 * Se incluyen anuncios donde la fecha sea mayor o igual a la actual o sea nula.
	 */
	@Query("SELECT t FROM TablonAnuncio t WHERE t.banda.idBanda = :idBanda " +
	       "AND (t.fechaExpira >= CURRENT_TIMESTAMP OR t.fechaExpira IS NULL)")
	List<TablonAnuncio> findActivosByBanda(@Param("idBanda") Integer idBanda);
	
	List<TablonAnuncio> findByBanda_IdBandaOrderByIdAnunciosDesc(Integer idBanda);
	
	/**
     * Busca anuncios activos considerando todos los tipos de destino:
     * Globales, los de la banda, individuales, o los exclusivos para Dueños.
     */
    @Query("SELECT t FROM TablonAnuncio t WHERE " +
           "(t.tipoDestino = 'GLOBAL' " +
           "OR (t.tipoDestino = 'BANDA' AND t.banda.idBanda = :idBanda) " +
           "OR (t.tipoDestino = 'INDIVIDUAL' AND t.idUsuarioDestino = :idUsuario) " +
           "OR (t.tipoDestino = 'DUENOS' AND :rolApp LIKE '%Dueñ%')) " +
           "AND (t.fechaExpira >= CURRENT_TIMESTAMP OR t.fechaExpira IS NULL) " +
           "ORDER BY t.idAnuncios DESC")
    List<TablonAnuncio> findNoticiasParaUsuario(
            @Param("idBanda") Integer idBanda, 
            @Param("idUsuario") Integer idUsuario,
            @Param("rolApp") String rolApp);
}