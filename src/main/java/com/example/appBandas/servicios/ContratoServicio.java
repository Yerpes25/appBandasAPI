package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Contrato;
import com.example.appBandas.modelos.Transaccion;
import com.example.appBandas.repositorios.ContratoRepository;
import com.example.appBandas.repositorios.TransaccionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los contratos firmados por la banda.
 */
@Service
public class ContratoServicio {

    private final ContratoRepository contratoRepository;
    private final TransaccionRepository transaccionRepository;

    public ContratoServicio(ContratoRepository contratoRepository, TransaccionRepository transaccionRepository) {
		super();
		this.contratoRepository = contratoRepository;
		this.transaccionRepository = transaccionRepository;
	}
    
    public Contrato guardarContrato(Contrato contrato) {
        // 1. Guardamos el contrato
        Contrato nuevoContrato = contratoRepository.save(contrato);

        // 2. Creamos la transacción automática en Tesorería
        Transaccion t = new Transaccion();
        t.setBanda(nuevoContrato.getBanda());
        t.setDescripcion("Contrato: " + nuevoContrato.getCliente());
        t.setCantidad(nuevoContrato.getImporteTotal());
        t.setTipo("Ingreso");
        t.setCategoria("Actuación");
        t.setFecha(nuevoContrato.getfFirma()); // O LocalDate.now()
        
        transaccionRepository.save(t);

        return nuevoContrato;
    }

	public List<Contrato> obtenerTodosLosContratos() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> obtenerContratoPorId(Integer id) {
        return contratoRepository.findById(id);
    }


    public void eliminarContrato(Integer id) {
        contratoRepository.deleteById(id);
    }
}