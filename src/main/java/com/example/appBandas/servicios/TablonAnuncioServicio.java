package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.TablonAnuncio;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.TablonAnuncioRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las notificaciones y avisos oficiales para la banda.
 */
@Service
public class TablonAnuncioServicio {

    private final TablonAnuncioRepository tablonAnuncioRepository;
    private final NotificacionServicio notificacionServicio;
    private final UsuarioRepository usuarioRepository;

    public TablonAnuncioServicio(TablonAnuncioRepository tablonAnuncioRepository,
			NotificacionServicio notificacionServicio, UsuarioRepository usuarioRepository) {
		super();
		this.tablonAnuncioRepository = tablonAnuncioRepository;
		this.notificacionServicio = notificacionServicio;
		this.usuarioRepository = usuarioRepository;
	}

	public List<TablonAnuncio> obtenerTodosLosAnuncios() {
        return tablonAnuncioRepository.findAll();
    }

    public Optional<TablonAnuncio> obtenerAnuncioPorId(Integer id) {
        return tablonAnuncioRepository.findById(id);
    }

    /**
     * Guarda el anuncio y dispara automáticamente una notificación de campana
     * según el tipo de destino (Global, Dueños o Individual).
     */
    public TablonAnuncio guardarAnuncio(TablonAnuncio anuncio) {
        TablonAnuncio guardado = tablonAnuncioRepository.save(anuncio);
        
        // --- LÓGICA DE NOTIFICACIÓN AUTOMÁTICA (CAMPANITA) ---
        String tituloBell = "📢 " + guardado.getTitulo();
        String mensajeBell = guardado.getMensaje();

        if ("GLOBAL".equals(guardado.getTipoDestino())) {
            notificacionServicio.enviarATodos(tituloBell, mensajeBell);
            
        } else if ("DUENOS".equals(guardado.getTipoDestino())) {
            notificacionServicio.enviarADuenos(tituloBell, mensajeBell);
            
        } else if ("INDIVIDUAL".equals(guardado.getTipoDestino()) && guardado.getIdUsuarioDestino() != null) {
            notificacionServicio.enviarIndividual(guardado.getIdUsuarioDestino(), tituloBell, mensajeBell);
            
        } else if (guardado.getBanda() != null) {
            // Si es un anuncio normal de banda, notificamos a todos sus músicos
            List<Usuario> musicos = usuarioRepository.findByBanda_IdBanda(guardado.getBanda().getIdBanda());
            for (Usuario u : musicos) {
                notificacionServicio.enviarIndividual(u.getIdUsuario(), tituloBell, mensajeBell);
            }
        }
        
        return guardado;
    }

    public void eliminarAnuncio(Integer id) {
        tablonAnuncioRepository.deleteById(id);
    }
    
    public List<TablonAnuncio> obtenerAnunciosPorBanda(Integer idBanda) {
        /*
         * Llamamos al metodo del repositorio que ya devuelve 
         * los datos ordenados de fecha mas reciente a mas antigua.
         */
        return tablonAnuncioRepository.findByBanda_IdBandaOrderByIdAnunciosDesc(idBanda);
    }
    
    /**
     * Obtiene la lista completa de anuncios que un usuario específico tiene permiso para ver:
     * Globales, los de su banda, y los mensajes individuales directos.
     */
    public List<TablonAnuncio> obtenerAnunciosCompletos(Integer idBanda, Integer idUsuario, String rolApp) {
        return tablonAnuncioRepository.findNoticiasParaUsuario(idBanda, idUsuario, rolApp);
    }
}