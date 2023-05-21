package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaHorarios;
import ar.edu.unju.fi.model.Turno;


@Controller
@RequestMapping("/paseos")
public class PaseosController {
	
	ListaHorarios listaDeHorarios = new ListaHorarios();
	
    @GetMapping("/horarios")
    public String getPaseos(Model model){
    	model.addAttribute("listaDeHorarios", listaDeHorarios.getHorarios());
        return "paseos" ;
    }
    
    @GetMapping("/nuevohorario")
    public String getNuevoHorarioPage(Model model){
    	Turno nuevoTurno = new Turno();
        model.addAttribute("formHorario", nuevoTurno);
        return "nuevohorario";
    }
    
    @PostMapping("/guardar")
    public ModelAndView ActualizarListadoHorariosPage(@ModelAttribute("formHorario")Turno formHorario) {
    	ModelAndView modelView = new ModelAndView("paseos");
    	listaDeHorarios.getHorarios().add(formHorario);
    	modelView.addObject("listaHorarios", listaDeHorarios.getHorarios());    	    
    	return modelView;
    }
    
    @GetMapping("/modificarHorarios/{dia}")
    public String getModificarPage(Model model, @PathVariable(value="dia")String dia) {
    	Turno turnoDia = new Turno();

        for(Turno lhora: listaDeHorarios.getHorarios()) {
        	if(lhora.getDia().equals(dia)){
        		turnoDia = lhora;
        		System.out.println(turnoDia.toString());
        		break;
        	}
        }
        model.addAttribute("turnoDia",turnoDia);
        return "modificar-horarios";
 
    }
    
    @GetMapping("/eliminarHorarios/{dia}")
    public String getEliminarPage(Model model, @PathVariable(value="dia")String dia) {
        for(Turno lhora: listaDeHorarios.getHorarios()) {
        	if(lhora.getDia().equals(dia)){
        		listaDeHorarios.getHorarios().remove(lhora);
        		break;
        	}
        }        
        return "redirect:/paseos/horarios";
 
    }
    @PostMapping("/confirmarCambio")
    public String confirmarCambios(@ModelAttribute("turnoDia")Turno modificado,Model model) {
    	for(Turno lhora: listaDeHorarios.getHorarios()) {
        	if(modificado.getDia().equals(lhora.getDia())){
        		lhora.setPaseador1(modificado.getPaseador1());
        		lhora.setPaseador2(modificado.getPaseador2());
        		lhora.setTurnoA(modificado.getTurnoA());
        		lhora.setTurnoB(modificado.getTurnoB());
        		break;
        	}
        }
        model.addAttribute("listaDeHorarios",listaDeHorarios.getHorarios());
        return "redirect:/paseos/horarios";
    	
    }                                                                                
    
}