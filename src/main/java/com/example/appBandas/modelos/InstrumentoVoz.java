package com.example.appBandas.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "instrumento_voz")
public class InstrumentoVoz {

	@EmbeddedId
	private InstrumentoVozId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idInstrumento")
	@JoinColumn(name = "idInstrumento")
	private Instrumento instrumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idVoz")
	@JoinColumn(name = "idVoz", referencedColumnName = "idVoz")
	private Voz voz;

	public InstrumentoVoz() {
	}

	public InstrumentoVozId getId() {
		return id;
	}

	public void setId(InstrumentoVozId id) {
		this.id = id;
	}

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz voz) {
		this.voz = voz;
	}
}
