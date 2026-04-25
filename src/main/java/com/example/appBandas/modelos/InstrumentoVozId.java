package com.example.appBandas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta para la tabla
 * Instrumentos_Voces. Es necesaria en JPA para tablas intermedias sin un ID
 * autoincremental simple.
 */

@Embeddable
public class InstrumentoVozId implements Serializable {

	@Column(name = "idInstrumento")
	private Integer idInstrumento;

	@Column(name = "idVoz")
	private Integer idVoz;

	public InstrumentoVozId() {
	}

	public InstrumentoVozId(Integer idInstrumento, Integer idVoz) {
		this.idInstrumento = idInstrumento;
		this.idVoz = idVoz;
	}

	public Integer getIdInstrumento() {
		return idInstrumento;
	}

	public void setIdInstrumento(Integer idInstrumento) {
		this.idInstrumento = idInstrumento;
	}

	public Integer getIdVoz() {
		return idVoz;
	}

	public void setIdVoz(Integer idVoz) {
		this.idVoz = idVoz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		InstrumentoVozId that = (InstrumentoVozId) o;
		return Objects.equals(idInstrumento, that.idInstrumento) && Objects.equals(idVoz, that.idVoz);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idInstrumento, idVoz);
	}
}