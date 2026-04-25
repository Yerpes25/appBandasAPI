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

@Service
public class ComponentesServicio {

    private final UsuarioRepository usuarioRepo;
    private final AsistenciaRepository asistenciaRepo;
    private final UsuarioInstrumentoRepository usuarioInstRepo;
    private final BandaRepository bandaRepository;    

    public ComponentesServicio(UsuarioRepository usuarioRepo, AsistenciaRepository asistenciaRepo,
			UsuarioInstrumentoRepository usuarioInstRepo, BandaRepository bandaRepository) {
		super();
		this.usuarioRepo = usuarioRepo;
		this.asistenciaRepo = asistenciaRepo;
		this.usuarioInstRepo = usuarioInstRepo;
		this.bandaRepository = bandaRepository;
	}

	/**
     * Genera la lista de músicos con su asistencia e instrumento para las tarjetas.
     */
    public List<Map<String, String>> obtenerListaComponentes(Integer idBanda) {
        List<Map<String, String>> listaTarjetas = new ArrayList<>();
        
        // 1. Buscamos a todos los usuarios de esta banda
        // (Asegúrate de que este método exista en tu UsuarioRepository, suele llamarse findByBanda_IdBanda)
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
    
 // Añade esto en tu ComponentesServicio.java
    public boolean crearNuevoMusico(Integer idBanda, Map<String, String> datos) {
        try {
            Usuario nuevoMusico = new Usuario();
            nuevoMusico.setNombre(datos.get("nombre"));
            nuevoMusico.setApellidos(datos.get("apellidos"));
            nuevoMusico.setDni(datos.get("dni"));
            nuevoMusico.setEmail(datos.get("email"));
            nuevoMusico.setTelefono(datos.get("telefono"));
            
            // Valores por defecto para un músico nuevo
            nuevoMusico.setPassword("1234");
            nuevoMusico.setRolApp("Musico");
            nuevoMusico.setActivo(true);
            nuevoMusico.setAprobado(true);

            // IMPORTANTE: Le asignamos la banda del dueño
            // (Necesitas tener el bandaRepo inyectado en este servicio)
            Banda bandaDueño = bandaRepository.findById(idBanda).orElse(null);
            if (bandaDueño == null) return false;
            
            nuevoMusico.setBanda(bandaDueño);

            usuarioRepo.save(nuevoMusico);

            // Nota: Aquí iría la lógica de guardar el Instrumento en la tabla "UsuarioInstrumento",
            // pero como los vamos a crear dinámicamente más adelante desde la BD, por ahora 
            // el usuario se guarda perfectamente y en la tarjeta aparecerá como "MÚSICO".
            
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar músico: " + e.getMessage());
            return false;
        }
    }
}