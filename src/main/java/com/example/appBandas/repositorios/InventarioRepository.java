package com.example.appBandas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Inventario;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Inventario.
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
	List<Inventario> findByBanda_IdBandaAndStockLessThan(Integer idBanda, Integer limiteStock);
	List<Inventario> findByBanda_IdBanda(Integer idBanda);
	
	// Lista completa para la tabla
	List<Inventario> findByBanda_IdBandaAndDescripcionContainingIgnoreCase(Integer idBanda, String descripcion);

    // Contadores para las tarjetas
	@Query("SELECT COALESCE(SUM(i.stock), 0) FROM Inventario i WHERE i.banda.idBanda = :idBanda")
    Integer sumarStockTotal(Integer idBanda);

    @Query("SELECT COALESCE(SUM(i.stock), 0) FROM Inventario i WHERE i.banda.idBanda = :idBanda AND LOWER(i.tipo) = LOWER(:tipo)")
    Integer sumarStockPorTipo(Integer idBanda, String tipo);

    @Query("SELECT COALESCE(SUM(i.stock), 0) FROM Inventario i WHERE i.banda.idBanda = :idBanda AND LOWER(i.estado) = LOWER(:estado)")
    Integer sumarStockPorEstado(Integer idBanda, String estado);
}