package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Voz;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Voz.
 */
@Repository
public interface VozRepository extends JpaRepository<Voz, Integer> {
}