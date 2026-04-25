package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Seccion;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Seccion.
 */
@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
}