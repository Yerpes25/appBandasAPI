package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Instrumento;

/**
 * Repositorio para gestionar las operaciones de persistencia de la entidad
 * Instrumento.
 */
@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {
}