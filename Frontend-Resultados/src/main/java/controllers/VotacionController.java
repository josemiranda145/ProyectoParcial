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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import services.VotacionService;

@Controller
@RequestMapping("/votacion")
public class VotacionController extends AbstractController {

	@Autowired
	private VotacionService votacionService;

	// Constructors -----------------------------------------------------------

	public VotacionController() {
		super();
	}

	@RequestMapping(value = "/getVotacion", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, String>> getVotacion(@RequestParam Integer idVotacion) {

		List<Map<String, String>> propuestas;

		propuestas = new LinkedList<Map<String, String>>();

		if (idVotacion == null) {
			// la peticion debe indicar una id, sin ella no sabemos a que
			// votacion se refiere
			throw new IllegalArgumentException(
					"No se ha indicado correctamente el id de la votacion");
		}

		propuestas = votacionService.getVotacion(idVotacion);

		// parseamos la informacion de la votacion para visualizacion

		return propuestas;
	}

}