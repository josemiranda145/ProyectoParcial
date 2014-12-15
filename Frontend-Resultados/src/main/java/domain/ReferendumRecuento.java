package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class ReferendumRecuento extends DomainEntity {
	private int idVotacionRecuento;
	private Collection<Propuesta> propuestas;

	public ReferendumRecuento() {
		super();
	}

	@Column(unique = true)
	public int getIdVotacionRecuento() {
		return idVotacionRecuento;
	}

	public void setIdVotacionRecuento(int idVotacionRecuento) {
		this.idVotacionRecuento = idVotacionRecuento;
	}

	@OneToMany(mappedBy = "referendumRecuento")
	public Collection<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(Collection<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}
}
