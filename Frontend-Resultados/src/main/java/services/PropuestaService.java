package services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PropuestaRepository;
import domain.Propuesta;

@Transactional
@Service
public class PropuestaService {

	// Managed repository

	@Autowired
	private PropuestaRepository propuestaRepository;

	public void save(Propuesta propuesta) {

		propuestaRepository.save(propuesta);
	}

	public List<Propuesta> save(List<Propuesta> propuestas) {

		return propuestaRepository.save(propuestas);
	}

	public Propuesta find(Integer idPregunta) {

		return propuestaRepository.findOne(idPregunta);
	}

	public List<Propuesta> testPropuestas() {
		List<Propuesta> res;
		res = new LinkedList<Propuesta>();
		Propuesta p1 = new Propuesta();
		p1.setPregunta("cuestion1");
		p1.setNumerosNo(200);
		p1.setNumerosSi(500);
		Propuesta p2 = new Propuesta();
		p2.setPregunta("cuestion2");
		p2.setNumerosNo(400);
		p2.setNumerosSi(100);

		Propuesta p3 = new Propuesta();
		p3.setPregunta("cuestion3");
		p3.setNumerosNo(500);
		p3.setNumerosSi(300);

		res.add(p1);
		res.add(p2);
		res.add(p3);
		return res;
	}

}
