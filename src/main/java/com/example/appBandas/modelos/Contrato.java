package com.example.appBandas.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que registra los acuerdos comerciales entre la banda y sus clientes.
 * Gestiona el importe total y las horas de servicio acordadas.
 */
@Entity
@Table(name = "Contratos")
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContrato")
	private Integer idContrato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanda", nullable = false)
	private Banda banda;

	@Column(name = "cliente", nullable = false, length = 100)
	private String cliente;

	@Column(name = "fFirma")
	private LocalDate fFirma;

	@Column(name = "importeTotal")
	private Double importeTotal;

	@Column(name = "horasFirmadas")
	private Double horasFirmadas;

	public Contrato() {
	}

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public LocalDate getfFirma() {
		return fFirma;
	}

	public void setfFirma(LocalDate fFirma) {
		this.fFirma = fFirma;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getHorasFirmadas() {
		return horasFirmadas;
	}

	public void setHorasFirmadas(Double horasFirmadas) {
		this.horasFirmadas = horasFirmadas;
	}
}