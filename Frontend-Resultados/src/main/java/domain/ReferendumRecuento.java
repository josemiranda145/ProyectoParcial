package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class ReferendumRecuento extends DomainEntity {
	private int idVotacionRecuento;
	private int idVotacionModificacion;
	private Collection<Propuesta> propuestas;

	public ReferendumRecuento() {
		super();
	}

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

	public int getIdVotacionModificacion() {
		return idVotacionModificacion;
	}

	public void setIdVotacionModificacion(int idVotacionModificacion) {
		this.idVotacionModificacion = idVotacionModificacion;
	}
}
