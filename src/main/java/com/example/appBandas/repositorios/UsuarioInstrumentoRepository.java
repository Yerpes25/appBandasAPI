package com.example.appBandas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.modelos.UsuarioInstrumentoId;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * UsuarioInstrumento. Utiliza una clave primaria compuesta
 * (UsuarioInstrumentoId).
 */
@Repository
public interface UsuarioInstrumentoRepository extends JpaRepository<UsuarioInstrumento, UsuarioInstrumentoId> {
	// Busca los instrumentos que toca un usuario
    List<UsuarioInstrumento> findByUsuario_IdUsuario(Integer idUsuario);
}