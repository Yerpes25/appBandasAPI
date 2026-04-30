package com.example.appBandas.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.repositorios.AsistenciaRepository;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.UsuarioInstrumentoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComponentesServicio {

	private final UsuarioRepository usuarioRepo;
	private final AsistenciaRepository asistenciaRepo;
	private final UsuarioInstrumentoRepository usuarioInstRepo;
	private final BandaRepository bandaRepository;
	private final InstrumentoVozServicio instrumentoVozServicio;

	public ComponentesServicio(UsuarioRepository usuarioRepo, AsistenciaRepository asistenciaRepo,
			UsuarioInstrumentoRepository usuarioInstRepo, BandaRepository bandaRepository,
			InstrumentoVozServicio instrumentoVozServicio) {
		super();
		this.usuarioRepo = usuarioRepo;
		this.asistenciaRepo = asistenciaRepo;
		this.usuarioInstRepo = usuarioInstRepo;
		this.bandaRepository = bandaRepository;
		this.instrumentoVozServicio = instrumentoVozServicio;
	}

	/**
	 * Genera la lista de músicos con su asistencia e instrumento para las tarjetas.
	 */
	public List<Map<String, String>> obtenerListaComponentes(Integer idBanda) {
		List<Map<String, String>> listaTarjetas = new ArrayList<>();

		// 1. Buscamos a todos los usuarios de esta banda
		// (Asegúrate de que este método exista en tu UsuarioRepository, suele llamarse
		// findByBanda_IdBanda)
		List<Usuario> musicos = usuarioRepo.findByBanda_IdBanda(idBanda);

		for (Usuario musico : musicos) {
			Map<String, String> tarjeta = new HashMap<>();

			// NOMBRE
			String nombreCompleto = musico.getNombre() + " " + musico.getApellidos();
			tarjeta.put("nombre", nombreCompleto);

			// ESTADO
			String estado = (musico.getActivo() != null && musico.getActivo()) ? "Activo" : "Excedencia";
			tarjeta.put("estado", estado);
			tarjeta.put("idUsuario", String.valueOf(musico.getIdUsuario()));

			// ASISTENCIA (Regla de 3)
			long totalEventos = asistenciaRepo.contarEventosTotalesDelUsuario(musico.getIdUsuario());
			long asistencias = asistenciaRepo.contarAsistenciasPositivas(musico.getIdUsuario());

			String porcentajeTexto = "100%"; // Por defecto si no hay eventos
			if (totalEventos > 0) {
				long porcentaje = Math.round(((double) asistencias / totalEventos) * 100);
				porcentajeTexto = porcentaje + "%";
			}
			tarjeta.put("asistencia", porcentajeTexto);

			// CARGO / INSTRUMENTO
			List<UsuarioInstrumento> instrumentos = usuarioInstRepo.findByUsuario_IdUsuario(musico.getIdUsuario());
			if (!instrumentos.isEmpty()) {
				// Cogemos el primer instrumento que toque
				String nombreInstrumento = instrumentos.get(0).getInstrumentoVoz().getInstrumento().getNombre();
				String voz = instrumentos.get(0).getInstrumentoVoz().getVoz().getNombre();
				tarjeta.put("cargo", nombreInstrumento + " (" + voz + ")");
			} else {
				// Si no tiene instrumento, le ponemos su rol (Dueño, Administrador, etc)
				tarjeta.put("cargo", musico.getRolApp() != null ? musico.getRolApp() : "MÚSICO");
			}

			listaTarjetas.add(tarjeta);
		}

		return listaTarjetas;
	}

	@Transactional
    public boolean crearNuevoMusico(Integer idBanda, Map<String, String> datos) {
        try {
            Usuario nuevoMusico = new Usuario();
            nuevoMusico.setNombre(datos.get("nombre"));
            nuevoMusico.setApellidos(datos.get("apellidos"));
            nuevoMusico.setDni(datos.get("dni"));
            nuevoMusico.setEmail(datos.get("email"));
            nuevoMusico.setTelefono(datos.get("telefono"));
            
            if (datos.containsKey("direccion")) {
                nuevoMusico.setDireccion(datos.get("direccion"));
            }
            
            nuevoMusico.setPassword(datos.containsKey("clave") ? datos.get("clave") : "1234");
            nuevoMusico.setRolApp("Musico");
            nuevoMusico.setActivo(true);
            nuevoMusico.setAprobado(true);
            
            nuevoMusico.setfAlta(java.time.LocalDate.now());
            nuevoMusico.setUltimoAcceso(java.time.LocalDateTime.now());

            Banda bandaDueño = bandaRepository.findById(idBanda).orElse(null);
            if (bandaDueño == null) return false;
            nuevoMusico.setBanda(bandaDueño);

            usuarioRepo.save(nuevoMusico);

            // VINCULAR EL INSTRUMENTO Y LA VOZ
            if (datos.containsKey("idInstrumento") && !datos.get("idInstrumento").isEmpty() &&
                datos.containsKey("idVoz") && !datos.get("idVoz").isEmpty()) {
                
                Integer idInst = Integer.parseInt(datos.get("idInstrumento"));
                Integer idVoz = Integer.parseInt(datos.get("idVoz"));
                
                instrumentoVozServicio.obtenerPorId(idInst, idVoz).ifPresent(instrumentoVoz -> {
                    UsuarioInstrumento ui = new UsuarioInstrumento();
                    ui.setUsuario(nuevoMusico);
                    ui.setInstrumentoVoz(instrumentoVoz);
                    
                    com.example.appBandas.modelos.UsuarioInstrumentoId uiId = new com.example.appBandas.modelos.UsuarioInstrumentoId();
                    uiId.setIdUsuario(nuevoMusico.getIdUsuario());
                    uiId.setIdInstrumento(idInst);
                    uiId.setIdVoz(idVoz);
                    ui.setId(uiId);
                    
                    usuarioInstRepo.save(ui);
                });
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar nuevo músico: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }
	
	@Transactional
	public boolean actualizarMusico(Integer idUsuario, Map<String, String> datos) {
	    try {
	        Usuario musico = usuarioRepo.findById(idUsuario).orElse(null);
	        if (musico == null) return false;

	        // 1. Actualizar datos básicos del Usuario[cite: 25]
	        musico.setNombre(datos.get("nombre"));
	        musico.setApellidos(datos.get("apellidos"));
	        musico.setDni(datos.get("dni"));
	        musico.setEmail(datos.get("email"));
	        musico.setTelefono(datos.get("telefono"));
	        musico.setDireccion(datos.get("direccion"));
	        if (datos.containsKey("clave") && datos.get("clave") != null) {
	            musico.setPassword(datos.get("clave"));
	        }
	        usuarioRepo.save(musico);

	        // 2. Gestionar Instrumento y Voz con Clave Compuesta[cite: 44, 45]
	        if (datos.get("idInstrumento") != null && !datos.get("idInstrumento").isEmpty()) {
	            Integer idInst = Integer.parseInt(datos.get("idInstrumento"));
	            Integer idVoz = Integer.parseInt(datos.get("idVoz"));

	            // A) Limpiar asignaciones anteriores para evitar duplicados en la clave compuesta
	            List<UsuarioInstrumento> viejas = usuarioInstRepo.findByUsuario_IdUsuario(idUsuario);
	            usuarioInstRepo.deleteAll(viejas);

	            // B) Crear la nueva asignación forzando el objeto de ID compuesto
	            instrumentoVozServicio.obtenerPorId(idInst, idVoz).ifPresent(iv -> {
	                UsuarioInstrumento ui = new UsuarioInstrumento();
	                ui.setUsuario(musico);
	                ui.setInstrumentoVoz(iv);
	                
	                com.example.appBandas.modelos.UsuarioInstrumentoId uiId = new com.example.appBandas.modelos.UsuarioInstrumentoId();
	                uiId.setIdUsuario(musico.getIdUsuario());
	                uiId.setIdInstrumento(idInst);
	                uiId.setIdVoz(idVoz);
	                ui.setId(uiId);

	                usuarioInstRepo.save(ui);
	            });
	        }
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}