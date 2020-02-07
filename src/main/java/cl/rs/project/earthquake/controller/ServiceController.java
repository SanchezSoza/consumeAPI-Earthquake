package cl.rs.project.earthquake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {

	@GetMapping(value = "/prueba")
	public String pruebaRest() {
		return "Hola Mundo";
	}
	
}
