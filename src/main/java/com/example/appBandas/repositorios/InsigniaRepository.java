package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Insignia;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Insignia.
 */
@Repository
public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {
}