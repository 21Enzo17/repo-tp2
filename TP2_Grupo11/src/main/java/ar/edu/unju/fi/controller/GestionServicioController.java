package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.service.IServicioService;

@Controller
@RequestMapping("gestion")
public class GestionServicioController {

	@Autowired
	@Qualifier("ServicioServiceMySql")
	private IServicioService paseosService;
	
	@GetMapping()
	private String getGestionPage() {
		return "gestion-servicio";
	}
	
	@GetMapping("/modificarHorario")
	private String getMofificar(Model model) {
		model.addAttribute("modificar",true);
		model.addAttribute("listaDeHorarios",paseosService.getListaOrdenada());
		return "gestion-servicio";
	}
	
	@GetMapping("/nuevoEmpleado")
	private String getNuevoEmpleadoPage(Model model) {
		model.addAttribute("empleado",paseosService.getEmpleado());
		return "nuevo-empleado";
	}
	
}
