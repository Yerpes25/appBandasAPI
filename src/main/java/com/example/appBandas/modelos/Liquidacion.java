package com.example.appBandas.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que registra los pagos efectuados a los músicos tras una actuación.
 * Se calcula en base a los beneficios de un contrato específico.
 */
@Entity
@Table(name = "Liquidaciones")
public class Liquidacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiquidaciones")
	private Integer idLiquidaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idContrato", nullable = false)
	private Contrato contrato;

	@Column(name = "importeAPagar", nullable = false)
	private Double importeAPagar;

	@Column(name = "fPago")
	private LocalDate fPago;

	public Liquidacion() {
	}

	public Integer getIdLiquidaciones() {
		return idLiquidaciones;
	}

	public void setIdLiquidaciones(Integer idLiquidaciones) {
		this.idLiquidaciones = idLiquidaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Double getImporteAPagar() {
		return importeAPagar;
	}

	public void setImporteAPagar(Double importeAPagar) {
		this.importeAPagar = importeAPagar;
	}

	public LocalDate getfPago() {
		return fPago;
	}

	public void setfPago(LocalDate fPago) {
		this.fPago = fPago;
	}
}