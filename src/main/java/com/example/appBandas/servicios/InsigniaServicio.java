package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.InsigniaOtorgadaDTO;
import com.example.appBandas.modelos.Insignia;
import com.example.appBandas.modelos.UsuarioInsignia;
import com.example.appBandas.modelos.UsuarioInsigniaId;
import com.example.appBandas.repositorios.AsistenciaRepository;
import com.example.appBandas.repositorios.InsigniaRepository;
import com.example.appBandas.repositorios.UsuarioInsigniaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los logros e insignias que pueden conseguir los
 * músicos.
 */
@Service
public class InsigniaServicio {

	private final InsigniaRepository insigniaRepository;
	private final AsistenciaRepository asistenciaRepository;
	private final UsuarioInsigniaRepository usuarioInsigniaRepository;

	public InsigniaServicio(InsigniaRepository insigniaRepository, AsistenciaRepository asistenciaRepository,
			UsuarioInsigniaRepository usuarioInsigniaRepository) {
		super();
		this.insigniaRepository = insigniaRepository;
		this.asistenciaRepository = asistenciaRepository;
		this.usuarioInsigniaRepository = usuarioInsigniaRepository;
	}

	public List<Insignia> obtenerTodasLasInsignias() {
		return insigniaRepository.findAll();
	}

	public Optional<Insignia> obtenerInsigniaPorId(Integer id) {
		return insigniaRepository.findById(id);
	}

	public Insignia guardarInsignia(Insignia insignia) {
		return insigniaRepository.save(insignia);
	}

	public void eliminarInsignia(Integer id) {
		insigniaRepository.deleteById(id);
	}

	/**
	 * Motor que comprueba si el usuario ha alcanzado alguna meta de asistencia. Se
	 * debe ejecutar justo despues de guardar un nuevo registro de Asistencia.
	 */
	public void comprobarInsigniasDeAsistencia(Integer idUsuario) {
		// 1. Contamos a cuantos eventos ha asistido realmente este usuario
		long totalAsistencias = asistenciaRepository.contarEnsayosAsistidosPositivos(idUsuario); 
		List<Insignia> todasLasInsignias = insigniaRepository.findAll();

		for (Insignia insignia : todasLasInsignias) {
			// Comprobamos si la insignia es de asistencia (por ejemplo, si su descripcion
			// contiene la palabra 'asistencia' o su meta encaja)
			if (insignia.getMeta() != null && totalAsistencias >= insignia.getMeta()) {

				// 3. Verificamos si el usuario YA tiene esta insignia para no darsela dos veces
				UsuarioInsigniaId idRelacion = new UsuarioInsigniaId(idUsuario, insignia.getIdInsignia());
				if (!usuarioInsigniaRepository.existsById(idRelacion)) {

					// 4. ¡Alcanzó la meta! Le otorgamos la insignia
					UsuarioInsignia nuevaInsignia = new UsuarioInsignia();
					nuevaInsignia.setId(idRelacion);
					nuevaInsignia.setfObtencion(LocalDate.now()); // Fecha de hoy

					usuarioInsigniaRepository.save(nuevaInsignia);
				}
			}
		}
	}

	/**
	 * Recupera las insignias de un usuario y las convierte en DTOs (paquetes). Este
	 * metodo es el que pide la aplicacion movil para pintar el carrusel.
	 */
	public List<InsigniaOtorgadaDTO> obtenerInsigniasDeUsuario(Integer idUsuario) {
		List<UsuarioInsignia> otorgadas = usuarioInsigniaRepository.findByUsuario_IdUsuario(idUsuario);
		List<InsigniaOtorgadaDTO> listaDto = new ArrayList<>();

		for (UsuarioInsignia registro : otorgadas) {
			InsigniaOtorgadaDTO dto = new InsigniaOtorgadaDTO();

			dto.setTitulo(registro.getInsignia().getNombre());
			dto.setIcono(registro.getInsignia().getIcono());
			dto.setMeta(registro.getInsignia().getMeta());
			// Si la fecha es nula, evitamos que pete poniendo la de hoy por defecto
			dto.setFechaOtorgada(registro.getfObtencion() != null ? registro.getfObtencion() : LocalDate.now());

			listaDto.add(dto);
		}

		return listaDto;
	}
}