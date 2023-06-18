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
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.service.IConsejoService;
import jakarta.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consejos")
public class ConsejoController {

	@Autowired
    IConsejoService consejoService;
	 /**
     * Método que muestra la página de consejos
     * @param model
     * @return consejos.html
     */
    @GetMapping("/listado")
    public ModelAndView getConsejos(Model model){
    	return consejoService.getConsejos(model);
    }
    
    /**
     * Método que muestra la página para crear un nuevo consejo
     * @param model
     * @return nuevo-consejo.html
     */
    @GetMapping("/nuevo-consejo")
    public ModelAndView getnuevoConsejo(Model model) { 	
    	return consejoService.getnuevoConsejo(model);
    }
    /**
     * Método que crea un nuevo consejo y lo agrega a la lista
     * @param listaConsejo
     * @return consejos.html
     */
 
    @PostMapping("/nuevo-consejo")
    public ModelAndView crearConsejo(@Valid @ModelAttribute("nuevoConsejo")Consejo nuevoConsejo, BindingResult result){
        return consejoService.crearConsejo(nuevoConsejo, result);
    }
    
    /**
     * Metodo que elimina un consejo de la lista
     * @param id
     * @param model
     * @return consejos.html
     */
    @GetMapping("/eliminar-consejos/{id}")
    public ModelAndView eliminarConsejo(@PathVariable(value="id")int id,Model model){
        return consejoService.eliminarConsejo(id, model);
    }
    /*
     * Metodo que permite editar un consejo por id
     * @param id
     * @param model
     * @return modificar-consejo.html
     */
    @GetMapping("/editar-consejos/{id}")
    public ModelAndView editarConsejos(@PathVariable(value="id")int id,Model model){
        return consejoService.editarConsejos(id, model);
    }
    /**
     * Metodo que permite guardar el consejo modificado
     * @param modificado
     * @return  consejos.html
     */
    @PostMapping("modificar-consejo")
    public ModelAndView modificarLista(@Valid @ModelAttribute("consejosEditar")Consejo modificado, BindingResult result){
    	return consejoService.modificarLista(modificado, result);
    }

    
    /**
     * Metodo que permite buscar consejos
     *	@param titulo, model
     * @return consejo.html
     */
  @GetMapping("buscar-consejo")
   public ModelAndView buscarPorTitulo(@RequestParam("titulo") String buscado, Model model){
       return consejoService.buscarPorTitulo(buscado,model);
   }
  
 }

