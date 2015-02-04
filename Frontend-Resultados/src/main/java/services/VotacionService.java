package services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Votacion;

@Transactional
@Service
public class VotacionService {

	// Managed repository

	public List<Map<String, String>> getVotacion(Integer idVotacion) {
		RestTemplate restTemplate = new RestTemplate();

		String urlModificacion = "http://localhost:8181/modificacion/results?votacionId="
				+ idVotacion;

		String jsonString = restTemplate.getForObject(urlModificacion,
				String.class);

		return parseoJsonAPropuestas(jsonString, idVotacion, false);
	}

	public List<Map<String, String>> parseoJsonAPropuestas(String jsonString,
			int idVotacion, boolean recuento) {
		List<Map<String, String>> propuestas;
		Votacion votacion;

		propuestas = new LinkedList<Map<String, String>>();

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<List<Map<String, String>>> typeRef = new TypeReference<List<Map<String, String>>>() {
		};

		try {
			// convert JSON string a una lista de Propuestas
			propuestas = mapper.readValue(jsonString, typeRef);

		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Error al obtener los datos de la votación:\n" + jsonString);
		}

		votacion = new Votacion();

		votacion.setPropuestas(propuestas);

		return propuestas;
	}

}
