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

import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.service.IServicioService;
import jakarta.validation.Valid;

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
		model.addAttribute("modificar", true);
		model.addAttribute("listaDeHorarios", paseosService.getListaOrdenada());
		model.addAttribute("listaDeEmpleados", paseosService.getlistEmpleados());
		return "gestion-servicio";
	}

	@GetMapping("/nuevoEmpleado")
	private String getNuevoEmpleadoPage(Model model) {
		model.addAttribute("formEmpleado", paseosService.getEmpleado());
		return "nuevo-empleado";
	}

	@PostMapping("/guardarEmpleado")
	public ModelAndView ActualizarListadoHorariosPage(@Valid @ModelAttribute("formEmpleado") Empleado formEmpleado,
			BindingResult result) {
		ModelAndView modelView;
		if (result.hasErrors()) {
			modelView = new ModelAndView("nuevo-empleado");
			modelView.addObject("formEmpleado", formEmpleado);
		} else {
			paseosService.guardar(formEmpleado);
			modelView = new ModelAndView("empleados");
			modelView.addObject("listaDeEmpleados", paseosService.getlistEmpleados());
		}
		return modelView;
	}

	@GetMapping("/eliminarEmpleado/{cod}")
	public String getEliminarPage(Model model, @PathVariable(value = "cod") Long cod) {
		paseosService.eliminar(cod);
		model.addAttribute("listaDeEmpleados", paseosService.getlistEmpleados());
		return "empleados";
	}

	@GetMapping("/modificarEliminarEmpleado")
	public String getmodEliPage(Model model) {
		model.addAttribute("listaDeEmpleados", paseosService.getlistEmpleados());
		return "empleados";
	}

	@GetMapping("/modificarEmpleado/{cod}")
	public String getModificarPage(Model model, @PathVariable(value = "cod") Long cod) {
		model.addAttribute("Emod", paseosService.getEmpleado(cod));
		return "modificar-Empleado";
	}

	@PostMapping("/guardarCambiosEmpleado")
	public ModelAndView confirmarCambios(@Valid @ModelAttribute("Emod") Empleado modificado, BindingResult result) {
		ModelAndView modelView;
		if (result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getObjectName());
			modelView = new ModelAndView("modificar-empleado");
			modelView.addObject("listaDeEmpleados", paseosService.getlistEmpleados());
			modelView.addObject("Emod", modificado);
		} else {
			modelView = new ModelAndView("empleados");
			System.out.println(modificado.getCod());
			paseosService.guardar(modificado);
			modelView.addObject("listaDeEmpleados", paseosService.getlistEmpleados());
		}
		return modelView;
	}

	@GetMapping("/consultaEmpleado")
	public String getConsultarPage(Model model) {
		model.addAttribute("alertaInfo", true);
		return "gestion-servicio";
	
	}
	
	@GetMapping("/consultarServicio")
	public String getConsultarServicioPage(Model model) {
		model.addAttribute("dias",paseosService.getSemana());
		model.addAttribute("consultaInfo", true);
		return "gestion-servicio";
	}
	@GetMapping("/consulta/{dia}")
	public String getConsultaDiaPage(Model model, @PathVariable(value = "dia") String dia) {
		if(paseosService.buscarPorNombre(dia).size()==0) {
			model.addAttribute("sinResultados",true);
			model.addAttribute("dias",paseosService.getSemana());
			model.addAttribute("consultaInfo", true);
		}else {
			model.addAttribute("dias",paseosService.getSemana());
			model.addAttribute("consultaInfo", true);
			model.addAttribute("resultados", paseosService.buscarPorNombre(dia));
			model.addAttribute("Result",true);
		}
		return "gestion-servicio";
	}
	
	@GetMapping("buscar")
	public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado) {
		ModelAndView modelView=new ModelAndView("gestion-servicio");
		if(paseosService.buscarPorNombre(buscado).size()!=0) {
			modelView.addObject("modificar",true);
			modelView.addObject("listaDeHorarios",paseosService.buscarPorNombre(buscado));
		}else {
			modelView.addObject("alertaB",true);			
		}
		return modelView;
	}

}