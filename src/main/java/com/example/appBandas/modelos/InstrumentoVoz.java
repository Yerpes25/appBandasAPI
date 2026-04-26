package com.example.appBandas.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	@JoinColumn(name = "idInstrumento", insertable = false, updatable = false)
	private Instrumento instrumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idVoz", insertable = false, updatable = false)
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
