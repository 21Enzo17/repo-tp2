package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Turno;
import ar.edu.unju.fi.service.IServicioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/paseos")
public class PaseoController {
	@Autowired
	@Qualifier("ServicioServiceMySql")
	private IServicioService paseosService;

	@GetMapping("/horarios")
	public String getPaseos(Model model) {
		model.addAttribute("listaDeHorarios", paseosService.getListaOrdenada());
		return "paseos";
	}

	@GetMapping("/nuevohorario")
	public String getNuevoHorarioPage(Model model) {
		if (paseosService.semanaCompleta()) {
			model.addAttribute("formHorario", paseosService.getTurno());
			model.addAttribute("diasDisponibles", paseosService.getDisponibles());
			model.addAttribute("paseadoresDisponibles", paseosService.getlistEmpleados());
			return "nuevohorario";
		} else {
			model.addAttribute("listaDeHorarios", paseosService.getListaOrdenada());
			model.addAttribute("alerta", true);
			return "paseos";
		}
	}

	@PostMapping("/guardar")
	public ModelAndView ActualizarListadoHorariosPage(@Valid @ModelAttribute("formHorario") Turno formHorario,
			BindingResult result) {
		ModelAndView modelView;
		if (result.hasErrors()) {
			modelView = new ModelAndView("nuevoHorario");
			modelView.addObject("formHorario", formHorario);
			modelView.addObject("diasDisponibles", paseosService.getDisponibles());
			modelView.addObject("paseadoresDisponibles",paseosService.getlistEmpleados());
			
		} else {
			modelView = new ModelAndView("paseos");
			modelView.addObject("listaDeHorarios", paseosService.guardarTurno(formHorario));
		}
		return modelView;
	}

	@GetMapping("/modificarHorarios/{dia}")
	public String getModificarPage(Model model, @PathVariable(value = "dia") String dia) {
		model.addAttribute("turnoDia", paseosService.getTurno(dia));
		model.addAttribute("empleadosDisponibles",paseosService.getlistEmpleados());
		return "modificar-horarios";
	}

	@GetMapping("/eliminarHorarios/{dia}")
	public String getEliminarPage(Model model, @PathVariable(value = "dia") String dia) {
		paseosService.eliminarHorario(dia);
		return "redirect:/paseos/horarios";
	}

	@PostMapping("/confirmarCambio")
	public ModelAndView confirmarCambios(@Valid @ModelAttribute("turnoDia") Turno modificado, BindingResult result) {
		ModelAndView modelView;
		if (result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getObjectName());
			modelView = new ModelAndView("modificar-horarios");
			modelView.addObject("empleadosDisponibles",paseosService.getlistEmpleados());
		} else {
			modelView = new ModelAndView("paseos");
			modelView.addObject("listaDeHorarios", paseosService.guardarCambios(modificado));
		}
		return modelView;
	}

	@GetMapping("buscarhorarios")
	public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado) {
		ModelAndView modelView=new ModelAndView("paseos");
		if(paseosService.buscarPorNombre(buscado).size()!=0) {
			modelView.addObject("listaDeHorarios",paseosService.buscarPorNombre(buscado));
		}else {
			modelView.addObject("alertaB",true);			
		}
		return modelView;
	}

	@GetMapping("/modificarHorario")
	public String getModificar(Model model) {
		return "gestion-servicio";
	}
}
