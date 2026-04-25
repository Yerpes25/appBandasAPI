package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.LecturaAnuncio;
import com.example.appBandas.modelos.LecturaAnuncioId;

/**
 * Repositorio para la tabla de lecturas de anuncios.
 */
@Repository
public interface LecturaAnuncioRepository extends JpaRepository<LecturaAnuncio, LecturaAnuncioId> {
}