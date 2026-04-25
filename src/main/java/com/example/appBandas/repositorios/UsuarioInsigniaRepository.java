package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.UsuarioInsignia;
import com.example.appBandas.modelos.UsuarioInsigniaId;

/**
 * Repositorio para la tabla de logros de los usuarios.
 */
@Repository
public interface UsuarioInsigniaRepository extends JpaRepository<UsuarioInsignia, UsuarioInsigniaId> {
}