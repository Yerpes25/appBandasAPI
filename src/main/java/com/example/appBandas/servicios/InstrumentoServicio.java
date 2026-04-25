package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Instrumento;
import com.example.appBandas.modelos.InstrumentoVoz;
import com.example.appBandas.modelos.InstrumentoVozId;
import com.example.appBandas.modelos.Seccion;
import com.example.appBandas.modelos.Voz;
import com.example.appBandas.repositorios.InstrumentoRepository;
import com.example.appBandas.repositorios.InstrumentoVozRepository;
import com.example.appBandas.repositorios.SeccionRepository;
import com.example.appBandas.repositorios.VozRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la logica de negocio de los instrumentos.
 * Permite guardar un instrumento de forma completa, creando automaticamente
 * su seccion si no existe, generando las particiones de voces solicitadas
 * y vinculando todo en la base de datos.
 */
@Service
public class InstrumentoServicio {

    private final InstrumentoRepository instrumentoRepository;
    private final SeccionRepository seccionRepository;
    private final VozRepository vozRepository;
    private final InstrumentoVozRepository instrumentoVozRepository;

    public InstrumentoServicio(InstrumentoRepository instrumentoRepository, SeccionRepository seccionRepository,
                               VozRepository vozRepository, InstrumentoVozRepository instrumentoVozRepository) {
        this.instrumentoRepository = instrumentoRepository;
        this.seccionRepository = seccionRepository;
        this.vozRepository = vozRepository;
        this.instrumentoVozRepository = instrumentoVozRepository;
    }

    public List<Instrumento> obtenerTodosLosInstrumentos() {
        return instrumentoRepository.findAll();
    }

    public Optional<Instrumento> obtenerInstrumentoPorId(Integer id) {
        return instrumentoRepository.findById(id);
    }

    public Instrumento guardarInstrumento(Instrumento instrumento) {
        return instrumentoRepository.save(instrumento);
    }

    public void eliminarInstrumento(Integer id) {
        instrumentoRepository.deleteById(id);
    }

    /**
     * Procesa un mapa de datos desde el frontend para crear un instrumento completo.
     * Crea la seccion y las voces si no existen en el sistema.
     */
    public Instrumento crearInstrumentoCompleto(Map<String, String> datos) throws Exception {
        
        // 1. Buscar o crear la Seccion
        String nombreSeccion = datos.get("seccion");
        Seccion seccionEncontrada = null;
        
        List<Seccion> todasLasSecciones = seccionRepository.findAll();
        for (Seccion s : todasLasSecciones) {
            if (s.getNombre().equalsIgnoreCase(nombreSeccion)) {
                seccionEncontrada = s;
                break;
            }
        }
        
        if (seccionEncontrada == null) {
            Seccion nuevaSeccion = new Seccion();
            nuevaSeccion.setNombre(nombreSeccion);
            seccionEncontrada = seccionRepository.save(nuevaSeccion);
        }

        // 2. Crear el Instrumento
        Instrumento nuevoInstrumento = new Instrumento();
        nuevoInstrumento.setNombre(datos.get("nombre"));
        nuevoInstrumento.setSeccion(seccionEncontrada);
        
        // Si la seccion tiene la palabra percusion, no es de viento
        if (nombreSeccion.toLowerCase().contains("percusión") || nombreSeccion.toLowerCase().contains("percusion")) {
            nuevoInstrumento.setEsViento(false);
        } else {
            nuevoInstrumento.setEsViento(true);
        }
        
        Instrumento instrumentoGuardado = instrumentoRepository.save(nuevoInstrumento);

        // 3. Trocear y guardar las Voces
        String textoVoces = datos.get("voces");
        if (textoVoces != null && !textoVoces.trim().isEmpty()) {
            
            String[] listaVoces = textoVoces.split(",");
            
            for (String nombreVozCrudo : listaVoces) {
                String nombreVoz = nombreVozCrudo.trim();
                if (nombreVoz.isEmpty()) {
                    continue;
                }

                // Buscar si la voz ya existe
                Voz vozEncontrada = null;
                List<Voz> todasLasVoces = vozRepository.findAll();
                for (Voz v : todasLasVoces) {
                    if (v.getNombre().equalsIgnoreCase(nombreVoz)) {
                        vozEncontrada = v;
                        break;
                    }
                }

                // Si no existe, la creamos
                if (vozEncontrada == null) {
                    Voz nuevaVoz = new Voz();
                    nuevaVoz.setNombre(nombreVoz);
                    vozEncontrada = vozRepository.save(nuevaVoz);
                }

                // Unimos el Instrumento con la Voz en la tabla intermedia
                InstrumentoVoz union = new InstrumentoVoz();
                union.setId(new InstrumentoVozId(instrumentoGuardado.getIdInstrumento(), vozEncontrada.getIdVoz()));
                union.setInstrumento(instrumentoGuardado);
                union.setVoz(vozEncontrada);
                
                instrumentoVozRepository.save(union);
            }
        }

        return instrumentoGuardado;
    }
}