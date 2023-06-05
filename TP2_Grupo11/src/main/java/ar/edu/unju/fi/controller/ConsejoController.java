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
import ar.edu.unju.fi.Listas.ListaConsejo;
import ar.edu.unju.fi.model.Consejo;
import jakarta.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consejos")
public class ConsejoController {

	@Autowired
	private ListaConsejo listaConsejos;
	@Autowired
	private Consejo formConsejo;
	 /**
     * Método que muestra la página de consejos
     * @param model
     * @return consejos.html
     */
    @GetMapping("/listado")
    public String getConsejos(Model model){
    	model.addAttribute("listaConsejos", listaConsejos.getListaConsejos());
        return "consejos";
    }
    
    /**
     * Método que muestra la página para crear un nuevo consejo
     * @param model
     * @return nuevo-consejo.html
     */
    @GetMapping("/nuevo-consejo")
    public String getnuevoConsejo(Model model) { 	
    	model.addAttribute("nuevoConsejo", formConsejo);
    	return "nuevo-consejo";
    }
    /**
     * Método que crea un nuevo consejo y lo agrega a la lista
     * @param listaConsejo
     * @return consejos.html
     */
 
    @PostMapping("/nuevo-consejo")
    public ModelAndView crearConsejo(@Valid @ModelAttribute("nuevoConsejo")Consejo nuevoConsejo, BindingResult result)
    {
    	ModelAndView modelView;
    	if(result.hasErrors()) {
    		modelView = new ModelAndView("nuevo-consejo");
    	}else {
    		modelView = new ModelAndView ("consejos");
    		listaConsejos.getListaConsejos().add(nuevoConsejo);
    		modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
    	}
    	return modelView;
    }
    
    /**
     * Metodo que elimina un consejo de la lista
     * @param id
     * @param model
     * @return consejos.html
     */
    @GetMapping("/eliminar-consejos/{id}")
    public String eliminarConsejo(@PathVariable(value="id")int id,Model model){
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(consejo.getId()==id){
                listaConsejos.getListaConsejos().remove(consejo);
                break;
            }
        }
        model.addAttribute("listaConsejos", listaConsejos.getListaConsejos());
        return "redirect:/consejos/listado";
    }
    /*
     * Metodo que permite editar un consejo por id
     * @param id
     * @param model
     * @return modificar-consejo.html
     */
    @GetMapping("/editar-consejos/{id}")
    public String editarConsejos(@PathVariable(value="id")int id,Model model){
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(consejo.getId()==id){
                model.addAttribute("consejosEditar", consejo);
                break;
            }
        }
        return "modificar-consejo";
    }
    /**
     * Metodo que permite guardar el consejo modificado
     * @param modificado
     * @return  consejos.html
     */
    @PostMapping("modificar-consejo")
    public ModelAndView modificarLista(@Valid @ModelAttribute("consejosEditar")Consejo modificado, BindingResult result){
    	 ModelAndView modelView;
    	if(result.hasErrors()) {
    	modelView = new ModelAndView("modificar-consejo");
    	}else {
    		for(Consejo consejo:listaConsejos.getListaConsejos()){
    			if(modificado.getId()==consejo.getId()){
    				consejo.setTitulo(modificado.getTitulo());
    				consejo.setDescripcion(modificado.getDescripcion());
    			break;
            	}
            }
            modelView = new ModelAndView("consejos");
            modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
         }
    	return modelView;
    }
  }
