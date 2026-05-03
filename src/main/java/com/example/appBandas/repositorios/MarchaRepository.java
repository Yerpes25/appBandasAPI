package com.example.appBandas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Marcha;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Marcha.
 */
@Repository
public interface MarchaRepository extends JpaRepository<Marcha, Integer> {
	List<Marcha> findTop5ByBanda_IdBandaOrderByFMontajeDesc(Integer idBanda);
	List<Marcha> findByBanda_IdBanda(Integer idBanda);
	List<Marcha> findByBanda_IdBandaOrderByFMontajeDesc(Integer idBanda);
}