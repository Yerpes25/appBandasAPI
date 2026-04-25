package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.GaleriaMultimedia;

/**
 * Repositorio para el acceso a datos de la galeria multimedia.
 */
@Repository
public interface GaleriaMultimediaRepository extends JpaRepository<GaleriaMultimedia, Integer> {
}