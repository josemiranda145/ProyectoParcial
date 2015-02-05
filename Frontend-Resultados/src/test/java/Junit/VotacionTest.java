package Junit;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ReferendumRecuentoService;
import utilities.PopulateDatabase;
import domain.Propuesta;
import domain.ReferendumRecuento;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
public class VotacionTest {
	@Autowired
	private ReferendumRecuentoService referendumRecuentoService;

	@Before
	public void setUp() throws Throwable {
		PopulateDatabase.main(null);
	}

	@Test
	public void pruebaParseoDatosVisualizacion() {
		List<Propuesta> propuestas = new LinkedList<Propuesta>();
		Propuesta p1 = new Propuesta();
		Propuesta p2 = new Propuesta();
		Propuesta p3 = new Propuesta();
		p1.setPregunta("¿propuesta 1?");
		p2.setPregunta("¿propuesta 2?");
		p3.setPregunta("¿propuesta 3?");
		p1.setNumerosNo(300);
		p2.setNumerosNo(200);
		p3.setNumerosNo(100);
		p1.setNumerosSi(100);
		p2.setNumerosSi(300);
		p3.setNumerosSi(200);
		propuestas.add(p1);
		propuestas.add(p2);
		propuestas.add(p3);
		Assert.notEmpty(referendumRecuentoService
				.ParseoDatosVisualizacion(propuestas));

	}

	@Test
	public void pruebaGuardarReferendumBaseDeDatos() {
		ReferendumRecuento referendumRecuento = new ReferendumRecuento();
		List<Propuesta> propuestas = new LinkedList<Propuesta>();
		Propuesta p1 = new Propuesta();
		Propuesta p2 = new Propuesta();
		Propuesta p3 = new Propuesta();
		p1.setPregunta("¿propuesta 1?");
		p2.setPregunta("¿propuesta 2?");
		p3.setPregunta("¿propuesta 3?");
		p1.setNumerosNo(300);
		p2.setNumerosNo(200);
		p3.setNumerosNo(100);
		p1.setNumerosSi(100);
		p2.setNumerosSi(300);
		p3.setNumerosSi(200);
		propuestas.add(p1);
		propuestas.add(p2);
		propuestas.add(p3);
		referendumRecuento.setPropuestas(propuestas);
		Assert.notNull(referendumRecuentoService.save(referendumRecuento));
	}

	@Test
	public void pruebaObtenerReferendumBaseDeDatos() {
		ReferendumRecuento referendumRecuento = new ReferendumRecuento();
		List<Propuesta> propuestas = new LinkedList<Propuesta>();
		Propuesta p1 = new Propuesta();
		Propuesta p2 = new Propuesta();
		Propuesta p3 = new Propuesta();
		p1.setPregunta("¿propuesta 1?");
		p2.setPregunta("¿propuesta 2?");
		p3.setPregunta("¿propuesta 3?");
		p1.setNumerosNo(300);
		p2.setNumerosNo(200);
		p3.setNumerosNo(100);
		p1.setNumerosSi(100);
		p2.setNumerosSi(300);
		p3.setNumerosSi(200);
		propuestas.add(p1);
		propuestas.add(p2);
		propuestas.add(p3);
		referendumRecuento.setPropuestas(propuestas);
		int id = 1000;

		referendumRecuento.setIdVotacionModificacion(id);
		referendumRecuentoService.save(referendumRecuento);
		Assert.notNull(referendumRecuentoService.findIdVotacionModificacion(id));

	}

	@Test
	public void pruebaParseoJsonAPropuestas() {
		List<Propuesta> res;
		res = new LinkedList<Propuesta>();
		String jsonString = "[{\"pregunta\":\"Propuesta1\",\"numerosSi\":100,\"numerosNo\":200},{\"pregunta\":\"Propuesta2\",\"numerosSi\":155,\"numerosNo\":50},{\"pregunta\":\"Propuesta3\",\"numerosSi\":1000,\"numerosNo\":200}]";
		res = referendumRecuentoService.parseoJsonAPropuestas(jsonString,
				1000000, false);
		Assert.notEmpty(res);
	}
}
