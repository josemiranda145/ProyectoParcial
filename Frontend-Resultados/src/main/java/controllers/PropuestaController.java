/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.PropuestaService;
import services.ReferendumRecuentoService;
import domain.Propuesta;
import domain.ReferendumRecuento;

@Controller
@RequestMapping("/rest")
public class VotacionController extends AbstractController {

	@Autowired
	private ReferendumRecuentoService referendumRecuentoService;
	@Autowired
	private PropuestaService propuestaService;

	// Constructors -----------------------------------------------------------

	public VotacionController() {
		super();
	}

	// metodo de prueba sin tener que comunicarnos con Recuento
	@RequestMapping(value = "/getPruebaPropuestas/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<Propuesta> pruebaGetARecuento(@PathVariable("id") int idVotacion) {

		return propuestaService.testPropuestas();

	}

	@RequestMapping(value = "/getVotacion", method = RequestMethod.GET)
	public @ResponseBody
	Object[] getVotacion(@RequestParam Integer idVotacion) {
		ReferendumRecuento referendumRecuento;
		List<Propuesta> propuestas;

		propuestas = new LinkedList<Propuesta>();

		if (idVotacion == null) {
			// la peticion debe indicar una id, sin ella no sabemos a que
			// votacion se refiere
			throw new IllegalArgumentException(
					"No se ha indicado correctamente el id de la votacion");
		}
		referendumRecuento = referendumRecuentoService
				.findIdVotacionRecuento(idVotacion);
		// si no es nula es que ya la hemos obtenido y guardado anteriormente,
		// simplemente la recuperamos de nuestra base de datos
		if (referendumRecuento == null) {

			// Es decir es una votación nueva, por lo que nos comunicamos con
			// recuento
			propuestas = referendumRecuentoService
					.getVotacionDeRecuento(idVotacion);

		} else {
			propuestas = (List<Propuesta>) referendumRecuento.getPropuestas();
		}

		// parseamos la informacion de la votacion para visualizacion
		Object[] res = referendumRecuentoService
				.ParseoDatosVisualizacion(propuestas);
		return res;

	}

}