package domain;

import java.util.Collection;
import java.util.Map;

public class Votacion extends DomainEntity {

	private Collection<Map<String, String>> propuestasVotacion;

	public Votacion() {
		super();
	}

	public Collection<Map<String, String>> getPropuestasVotacion() {
		return propuestasVotacion;
	}

	public void setPropuestas(Collection<Map<String, String>> propuestasVotacion) {
		this.propuestasVotacion = propuestasVotacion;
	}

	public void addPropuesta(Map<String, String> propuestaVotacion) {
		this.propuestasVotacion.add(propuestaVotacion);
	}
}
