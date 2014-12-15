package domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Propuesta extends DomainEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4470122022562237716L;
	/**
	 * 
	 */
	private String pregunta;
	private Integer numeroSi;
	private Integer numeroNo;

	private ReferendumRecuento referendumRecuento;

	public Propuesta() {
		super();
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Integer getNumeroSi() {
		return numeroSi;
	}

	public void setNumeroSi(Integer numeroSi) {
		this.numeroSi = numeroSi;
	}

	public Integer getNumeroNo() {
		return numeroNo;
	}

	public void setNumeroNo(Integer numeroNo) {
		this.numeroNo = numeroNo;
	}

	@Valid
	@ManyToOne(optional = false)
	@NotNull
	public ReferendumRecuento getReferendumRecuento() {
		return referendumRecuento;
	}

	public void setReferendumRecuento(ReferendumRecuento referendumRecuento) {
		this.referendumRecuento = referendumRecuento;
	}

}
