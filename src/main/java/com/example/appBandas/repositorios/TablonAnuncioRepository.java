package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.TablonAnuncio;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * TablonAnuncio.
 */
@Repository
public interface TablonAnuncioRepository extends JpaRepository<TablonAnuncio, Integer> {
}