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
	private Integer numerosSi;
	private Integer numerosNo;

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

	public Integer getNumerosSi() {
		return numerosSi;
	}

	public void setNumerosSi(Integer numerosSi) {
		this.numerosSi = numerosSi;
	}

	public Integer getNumerosNo() {
		return numerosNo;
	}

	public void setNumerosNo(Integer numerosNo) {
		this.numerosNo = numerosNo;
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
