package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones de persistencia de la entidad
 * Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	/**
	 * Busca un usuario por su DNI.
	 * 
	 * @param dni El documento de identidad.
	 * @return Un Optional con el usuario si existe.
	 */
	Optional<Usuario> findByDni(String dni);

	/**
	 * Busca un usuario por su email para el inicio de sesión.
	 * 
	 * @param email El correo electrónico.
	 * @return Un Optional con el usuario si existe.
	 */
	Optional<Usuario> findByEmail(String email);

	/**
	 * Consulta manual para contar cuantas bandas existen en total.
	 * 
	 * @return El numero total de bandas.
	 */
	@Query("SELECT COUNT(b) FROM Usuario b")
	long contarTodosLosUsuarios();

	long countByBanda_IdBanda(Integer idBanda);

	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.banda.idBanda = :idBanda AND u.ultimoAcceso >= :fechaLimite")
	long contarMusicosActivos(Integer idBanda, LocalDateTime fechaLimite);
	
	/**
     * Busca todos los usuarios que pertenecen a una banda específica.
     * Spring entiende "Banda_IdBanda" como: busca en la entidad Banda el campo idBanda.
     */
    List<Usuario> findByBanda_IdBanda(Integer idBanda);
}