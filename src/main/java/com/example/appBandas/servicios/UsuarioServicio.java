package com.example.appBandas.servicios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.appBandas.dtos.ComponenteDTO;
import com.example.appBandas.modelos.InstrumentoVoz;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.modelos.UsuarioCargo;
import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.repositorios.UsuarioCargoRepository;
import com.example.appBandas.repositorios.UsuarioInstrumentoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar toda la lógica de negocio relacionada con los usuarios de la banda.
 * Aquí se realizan operaciones como buscar músicos, registrarlos, actualizarlos o darlos de baja,
 * conectando los controladores web con la base de datos a través del repositorio.
 */
@Service
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioInstrumentoRepository usuarioInstrumentoRepository;
    private final UsuarioCargoRepository usuarioCargoRepository;
    private final NotificacionServicio notificacionServicio;

    public UsuarioServicio(UsuarioRepository usuarioRepository,
			UsuarioInstrumentoRepository usuarioInstrumentoRepository, 
            UsuarioCargoRepository usuarioCargoRepository,
            NotificacionServicio notificacionServicio) { 
		this.usuarioRepository = usuarioRepository;
		this.usuarioInstrumentoRepository = usuarioInstrumentoRepository;
		this.usuarioCargoRepository = usuarioCargoRepository;
        this.notificacionServicio = notificacionServicio; 
	}

	public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerUsuarioPorDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        boolean esNuevo = (usuario.getIdUsuario() == null);
        
        Usuario guardado = usuarioRepository.save(usuario);

        // Si es un registro nuevo y tiene banda asignada, mandamos el aviso
        if (esNuevo && guardado.getBanda() != null) {
            String nombreBanda = guardado.getBanda().getNombre();
            notificacionServicio.enviarIndividual(
                guardado.getIdUsuario(), 
                "¡Bienvenido a " + nombreBanda + "!", 
                "Te han dado de alta correctamente en la banda. ¡A disfrutar!"
            );
        }
        return guardado;
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    public long obtenerTodosLosUsuariosPorNumero() {
        return usuarioRepository.contarTodosLosUsuarios();
    }
    
    /**
     * Obtiene la lista completa de usuarios que pertenecen a una banda concreta.
     */
    public List<Usuario> obtenerUsuariosPorBanda(Integer idBanda) {
        return usuarioRepository.findByBanda_IdBanda(idBanda);
    }
    
    public List<ComponenteDTO> obtenerDetallesComponentes(Integer idBanda) {
        List<com.example.appBandas.modelos.Usuario> usuarios = usuarioRepository.findByBanda_IdBanda(idBanda);
        List<ComponenteDTO> listaFinal = new java.util.ArrayList<>();

        for (Usuario u : usuarios) {
            ComponenteDTO dto = new ComponenteDTO();
            
            // 1. Nombre Completo
            String nombre = u.getNombre() != null ? u.getNombre() : "";
            String apellidos = u.getApellidos() != null ? u.getApellidos() : "";
            dto.setNombreCompleto((nombre + " " + apellidos).trim());
            dto.setFotoPerfil(u.getFotoPerfil());

            // 2. Extraer el Cargo Real
            List<UsuarioCargo> ucList = usuarioCargoRepository.findByUsuario_IdUsuario(u.getIdUsuario());
            if (!ucList.isEmpty()) {
                // Obtenemos el nombre del cargo. 
                // (Si en tu clase RolCargo el atributo se llama 'descripcion', cambia getNombre() por getDescripcion())
                dto.setCargo(ucList.get(0).getCargo().getNombre()); 
            } else {
                dto.setCargo("Músico"); // Cargo por defecto si no está en la junta
            }

            // 3. Extraer el Instrumento y la Voz Reales
            List<UsuarioInstrumento> uiList = usuarioInstrumentoRepository.findByUsuario_IdUsuario(u.getIdUsuario());
            if (!uiList.isEmpty()) {
                InstrumentoVoz iv = uiList.get(0).getInstrumentoVoz();
                // Navegamos por las entidades para sacar los nombres
                String nomInst = iv.getInstrumento().getNombre(); 
                String nomVoz = iv.getVoz().getNombre();
                dto.setInstrumentoYVoz(nomInst + " / " + nomVoz);
            } else {
                dto.setInstrumentoYVoz("Sin instrumento asignado");
            }

            listaFinal.add(dto);
        }
        return listaFinal;
    }
    
    public long contarUsuariosPorBanda(Integer idBanda) {
        return usuarioRepository.countByBanda_IdBanda(idBanda);
    }
    
    /**
     * Transfiere la propiedad de la banda. Busca al dueño actual y lo degrada a Músico,
     * para luego ascender al nuevo usuario seleccionado al rol de Dueño.
     */
    public void transferirPropiedadDueno(Integer idBanda, Integer idNuevoDueno) {
        List<Usuario> usuariosBanda = usuarioRepository.findByBanda_IdBanda(idBanda);
        
        for (Usuario u : usuariosBanda) {
            if (u.getRolApp() != null && (u.getRolApp().toLowerCase().contains("due") || u.getRolApp().toLowerCase().contains("dueñ"))) {
                u.setRolApp("Músico");
                usuarioRepository.save(u);
            }
        }
        
        Optional<Usuario> nuevoDuenoOpt = usuarioRepository.findById(idNuevoDueno);
        if (nuevoDuenoOpt.isPresent()) {
            Usuario nuevoDueno = nuevoDuenoOpt.get();
            nuevoDueno.setRolApp("Dueño");
            usuarioRepository.save(nuevoDueno);
        }
    }
}