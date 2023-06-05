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
import ar.edu.unju.fi.Listas.ListaHorarios;
import ar.edu.unju.fi.model.Dia;
import ar.edu.unju.fi.model.Turno;
import jakarta.validation.Valid;
import ar.edu.unju.fi.Listas.ListaSemana;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/paseos")
public class PaseosController {
	
	@Autowired
	ListaHorarios listaDeHorarios ;

	@Autowired
	ListaSemana semana;
	
    /**
     * retorna pagina paseos
     * @param model
     * @return  paseos.html
     */
    @GetMapping("/horarios")
    public String getPaseos(Model model){
    	List<Turno> listaOrdenada=new ArrayList<Turno>();
    	for(int i=1;i<=6;i++) {
    		for(Turno turno: listaDeHorarios.getHorarios()) {
    			if(turno.getCod()==i) {
    				listaOrdenada.add(turno);
    			}
    		}
    	}
    	model.addAttribute("listaDeHorarios", listaOrdenada);
        return "paseos" ;
    }
    /**
     * agrega un nuevo horario de paseos
     * @param model
     * @return formulario de Alta
     */
    @GetMapping("/nuevohorario")
    public String getNuevoHorarioPage(Model model){
    	List<String> disponibles = new ArrayList<>();
    	
        if(listaDeHorarios.getHorarios().size()<6){
            Turno nuevoTurno = new Turno();
            for(Dia dia: semana.getSemana()) {
            		if(listaDeHorarios.existe(dia.getNombre())==false) {
            			disponibles.add(dia.getNombre());
            		}
            }
            model.addAttribute("formHorario", nuevoTurno);
            model.addAttribute("diasDisponibles",disponibles);
            return "nuevohorario"; 
        }
        boolean alerta=true;
    	List<Turno> listaOrdenada=new ArrayList<Turno>();
    	for(int i=1;i<=6;i++) {
    		for(Turno turno: listaDeHorarios.getHorarios()) {
    			if(turno.getCod()==i) {
    				listaOrdenada.add(turno);
    			}
    		}
    	}

        model.addAttribute("listaDeHorarios", listaOrdenada);
        model.addAttribute("alerta", alerta);
        return "paseos";
    }
    /**
     * confirmar un Nuevo Horario cargado
     * @param formHorario
     * @return paseos.html
     */
    @PostMapping("/guardar")
    public ModelAndView ActualizarListadoHorariosPage(@Valid @ModelAttribute("formHorario")Turno formHorario,BindingResult result) {
    	    	
    	if(result.hasErrors()) {
    		System.out.println(result.getErrorCount());
    		System.out.println(result.getObjectName());
	    	ModelAndView modelView = new ModelAndView("nuevoHorario");
	    	List<String> disponibles = new ArrayList<>();
	    	
	            for(Dia dia: semana.getSemana()) {
	            		if(listaDeHorarios.existe(dia.getNombre())==false) {
	            			disponibles.add(dia.getNombre());
	            		}
	            }
	    	modelView.addObject("formHorario", formHorario);
	    	modelView.addObject("diasDisponibles", disponibles);
	    	return modelView;

    	}else {
    	
            switch(formHorario.getDia()) {
	        case "Lunes":
	        	formHorario.setCod(1);
	            break;
	        case "Martes":
	        	formHorario.setCod(2);
	            break;
	        case "Miércoles":
	        	formHorario.setCod(3);
	            break;
	        case "Jueves":
	        	formHorario.setCod(4);
	            break;
	        case "Viernes":
	        	formHorario.setCod(5);
	            break;
	        case "Sábado":
	        	formHorario.setCod(6);
	            break;
	        default:
	        	formHorario.setCod(0);
	        	}
            
	    	 listaDeHorarios.getHorarios().add(formHorario);
	    	 List<Turno> listaOrdenada=new ArrayList<Turno>();
	     	for(int i=1;i<=6;i++) {
	     		for(Turno turno: listaDeHorarios.getHorarios()) {
	     			if(turno.getCod()==i) {
	     				listaOrdenada.add(turno);
	     			}
	     		}
	     	}
	    	ModelAndView modelView = new ModelAndView("paseos");
	    	modelView.addObject("listaDeHorarios", listaOrdenada);
	    	return modelView;
    	}
    }
    /**
     * Editar un dia de Horarios ya existente
     * @param model
     * @param dia tomado automaticamente
     * @return 
     */
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
    
    /**
     * Dar de baja un dia de Horarios en la lista
     * @param model
     * @param dia seleccionado automaticamente
     * @return
     */
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
    /**
     * Confirmar cambios de un Horario Editado
     * @param modificado
     * @param model
     * @return paseos.html
     */
    @PostMapping("/confirmarCambio")
    public ModelAndView confirmarCambios(@Valid @ModelAttribute("turnoDia")Turno modificado,Model model,BindingResult result) {
    	ModelAndView modelView = new ModelAndView("modificar-horario");

    	if(result.hasErrors()) {
    		System.out.println(result.getErrorCount());
    		System.out.println(result.getObjectName());
	    	modelView.addObject("turnoDia", modificado);
	    
    	}
    	else {
    	for(Turno lhora: listaDeHorarios.getHorarios()) {
        	if(modificado.getDia().equals(lhora.getDia())){
        		lhora.setPaseador1(modificado.getPaseador1());
        		lhora.setPaseador2(modificado.getPaseador2());
        		lhora.setTurnoA(modificado.getTurnoA());
        		lhora.setTurnoB(modificado.getTurnoB());
        		break;
        	}
        }
    	modelView = new ModelAndView("paseos");
    	List<Turno> listaOrdenada=new ArrayList<Turno>();
    	for(int i=1;i<=6;i++) {
    		for(Turno turno: listaDeHorarios.getHorarios()) {
    			if(turno.getCod()==i) {
    				listaOrdenada.add(turno);
    			}
    		}
    	}
    	modelView.addObject("listaDeHorarios", listaOrdenada);    	    
    	}
        return modelView;
    	
    }                                                                                
}
