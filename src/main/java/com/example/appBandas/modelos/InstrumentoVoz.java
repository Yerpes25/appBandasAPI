package com.example.appBandas.modelos;

import jakarta.persistence.*;

/**
 * Entidad que define las combinaciones permitidas entre un instrumento y una
 * voz. Evita que se asigne una voz incorrecta a un instrumento en el sistema.
 */
@Entity
@Table(name = "Instrumentos_Voces")
public class InstrumentoVoz {

	@EmbeddedId
	private InstrumentoVozId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idInstrumento")
	@JoinColumn(name = "idInstrumento")
	private Instrumento instrumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idVoz")
	@JoinColumn(name = "idVoz")
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
