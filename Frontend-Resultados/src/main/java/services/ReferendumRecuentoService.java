package services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import repositories.ReferendumRecuentoRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Propuesta;
import domain.ReferendumRecuento;

@Transactional
@Service
public class ReferendumRecuentoService {

	// Managed repository

	@Autowired
	private ReferendumRecuentoRepository referendumRecuentoRepository;
	@Autowired
	private PropuestaService propuestaService;

	public ReferendumRecuento save(ReferendumRecuento referendumRecuento) {

		return referendumRecuentoRepository.save(referendumRecuento);
	}

	public List<ReferendumRecuento> save(
			List<ReferendumRecuento> referendumRecuentos) {

		return referendumRecuentoRepository.save(referendumRecuentos);
	}

	public ReferendumRecuento findIdVotacionRecuento(int idVotacion) {
		return referendumRecuentoRepository.findIdVotacionRecuento(idVotacion);
	}

	public ReferendumRecuento findIdVotacionModificacion(Integer idModificacion) {
		return referendumRecuentoRepository
				.findIdVotacionModificacion(idModificacion);
	}

	public List<Propuesta> getVotacionDeRecuento(Integer idVotacion) {

		RestTemplate restTemplate = new RestTemplate();

		// esto es para probar, se comunica con nosotros mismo sin importar el
		// idVotacion. crea una lista de propuestas inventadas
		// asi probamos que consumimos bien el supuesto json que nos manda
		// Recuento

		String urlRecuento = "http://localhost:8080/Frontend-Resultados/rest/getPruebaPropuestas/14.do";

		// urlRecuento
		// ="http://localhost/test/recuento2?idVotacion="+idVotacion;

		String jsonString = restTemplate
				.getForObject(urlRecuento, String.class);

		return parseoJsonAPropuestas(jsonString, idVotacion, true);
	}

	public List<Propuesta> getVotacionDeModificacion(Integer idModificacion) {
		RestTemplate restTemplate = new RestTemplate();

		// String urltestModificacion =
		// "http://localhost:8080/Frontend-Resultados/rest/getPruebaPropuestas/14.do";

		String urlModificacion = "http://localhost:8181/modificacion/results?votacionId="
				+ idModificacion;

		String jsonString = restTemplate.getForObject(urlModificacion,
				String.class);

		return parseoJsonAPropuestas(jsonString, idModificacion, false);
	}

	public List<Propuesta> parseoJsonAPropuestas(String jsonString,
			int idVotacion, boolean recuento) {
		List<Propuesta> propuestas;

		ReferendumRecuento referendumRecuento;
		propuestas = new LinkedList<Propuesta>();

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<List<Propuesta>> typeRef = new TypeReference<List<Propuesta>>() {
		};

		try {
			// convert JSON string a una lista de Propuestas
			propuestas = mapper.readValue(jsonString, typeRef);

		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Error al obtener los datos de la votación:\n" + jsonString);
		}
		referendumRecuento = new ReferendumRecuento();
		if (recuento) {
			referendumRecuento.setIdVotacionRecuento(idVotacion);
		} else {
			referendumRecuento.setIdVotacionModificacion(idVotacion);

		}
		referendumRecuento = save(referendumRecuento);
		for (Propuesta p : propuestas) {
			p.setReferendumRecuento(referendumRecuento);
			propuestaService.save(p);

		}

		return propuestas;
	}

	public Object[] ParseoDatosVisualizacion(List<Propuesta> propuestas) {
		int cont = 0;
		Object[] res = new Object[propuestas.size()];

		for (Propuesta p : propuestas) {
			Object res2[] = new Object[3];
			res2[0] = p.getPregunta();
			res2[1] = p.getNumerosSi();
			res2[2] = p.getNumerosNo();
			res[cont] = res2;
			cont++;
		}
		return res;
	}

}
