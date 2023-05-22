package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.unju.fi.Listas.ListaConsejos;
import ar.edu.unju.fi.model.Consejo;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consejos")
public class ConsejosController {
	
	ListaConsejos listaConsejos = new ListaConsejos();
		
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
    	Consejo nuevoConsejo = new Consejo();    	
    	model.addAttribute("nuevoConsejo", nuevoConsejo);
    	return "nuevo-consejo";
    }
    
    
    /**
     * Método que crea un nuevo consejo y lo agrega a la lista
     * @param listaConsejo
     * @return consejos.html
     */
    @PostMapping("/nuevo-consejo")
    public ModelAndView crearConsejo(Consejo nuevoConsejo)
    {
    	ModelAndView modelAndView = new ModelAndView("consejos");
    	listaConsejos.getListaConsejos().add(nuevoConsejo);
    	modelAndView.addObject("listaConsejos",listaConsejos.getListaConsejos());
    	return modelAndView;
    }
    
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
    
    @GetMapping("/editar-consejos/{id}")
    public String editarConsejos(@PathVariable(value="id")int id,Model model){
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(consejo.getId()==id){
                model.addAttribute("consejosEditar", consejo);
                System.out.println(consejo.toString());
                break;
            }
        }
        return "modificar-consejo";
    }
    @PostMapping("modificar-consejo")
    public String modificarLista(@ModelAttribute("encontrado")Consejo modificado){
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(modificado.getId()==consejo.getId()){
                consejo.setTitulo(modificado.getTitulo());
                consejo.setDescripcion(modificado.getDescripcion());
                break;
            }
        }
        return "redirect:/consejos/listado";
    }
    

}
