package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Contrato;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * Contrato.
 */
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
	@Query("SELECT COALESCE(SUM(c.importeTotal), 0) FROM Contrato c WHERE c.banda.idBanda = :idBanda AND c.fFirma BETWEEN :inicio AND :fin")
	Double sumarIngresosPorFechas(Integer idBanda, java.time.LocalDate inicio, java.time.LocalDate fin);
}