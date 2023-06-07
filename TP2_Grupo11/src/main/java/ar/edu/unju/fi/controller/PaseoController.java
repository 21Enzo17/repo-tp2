package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Turno;
import ar.edu.unju.fi.service.IServicioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/paseos")
public class PaseoController {
	@Autowired
	private IServicioService paseosService;
    /**
     * retorna pagina paseos
     * @param model
     * @return  paseos.html
     */
    @GetMapping("/horarios")
    public String getPaseos(Model model){
    	model.addAttribute("listaDeHorarios", paseosService.getListaOrdenada());
        return "paseos" ;
    }
    /**
     * agrega un nuevo horario de paseos
     * @param model
     * @return formulario de Alta
     */
    @GetMapping("/nuevohorario")
    public String getNuevoHorarioPage(Model model){	
        if(paseosService.semanaCompleta()){
            model.addAttribute("formHorario", paseosService.getTurno());
            model.addAttribute("diasDisponibles",paseosService.getDisponibles());
            return "nuevohorario"; 
        }else {
        model.addAttribute("listaDeHorarios", paseosService.getListaOrdenada());
        model.addAttribute("alerta", true);
          return "paseos";
        }
    }
    /**
     * confirmar un Nuevo Horario cargado
     * @param formHorario
     * @return paseos.html
     */
    @PostMapping("/guardar")
    public ModelAndView ActualizarListadoHorariosPage(@Valid @ModelAttribute("formHorario")Turno formHorario,BindingResult result) {
    	ModelAndView modelView;
    	if(result.hasErrors()) {
	    	modelView = new ModelAndView("nuevoHorario");
	    	modelView.addObject("formHorario", formHorario);
	    	modelView.addObject("diasDisponibles", paseosService.getDisponibles());
    	}else {
	    	modelView = new ModelAndView("paseos");
	    	modelView.addObject("listaDeHorarios", paseosService.guardarTurno(formHorario));
    	}
    	return modelView;
    }
    /**
     * Editar un dia de Horarios ya existente
     * @param model
     * @param dia tomado automaticamente
     * @return 
     */
    @GetMapping("/modificarHorarios/{dia}")
    public String getModificarPage(Model model, @PathVariable(value="dia")String dia) {
        model.addAttribute("turnoDia",paseosService.getTurno(dia));
        return "modificar-horarios";
    }
    /**
     * Dar de baja un dia de Horarios en la lista
     * @param model
     * @param dia seleccionado automaticamente
     * @return
     */
    @GetMapping("/eliminarHorarios/{dia}")
    public String getEliminarPage(Model model, @PathVariable(value="dia")String dia) {       
    	paseosService.eliminarHorario(dia);
        return "redirect:/paseos/horarios";
    } 
    /**
     * Confirmar cambios de un Horario Editado
     * @param modificado
     * @param model
     * @return paseos.html
     */
    @PostMapping("/confirmarCambio")
    public ModelAndView confirmarCambios(@Valid @ModelAttribute("turnoDia")Turno modificado,BindingResult result) {
    	ModelAndView modelView;
    	if(result.hasErrors()) {
    		System.out.println(result.getErrorCount());
    		System.out.println(result.getObjectName());
			modelView = new ModelAndView("modificar-horarios");
    	}else {
			modelView = new ModelAndView("paseos");
			modelView.addObject("listaDeHorarios", paseosService.guardarCambios(modificado));    	    
    	}
        return modelView;	
    }                                                                                
}