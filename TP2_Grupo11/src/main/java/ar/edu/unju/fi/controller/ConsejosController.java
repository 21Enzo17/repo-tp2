package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.unju.fi.Listas.ListaConsejos;
import ar.edu.unju.fi.model.Consejo;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/consejos")
public class ConsejosController {
	
	ListaConsejos listaConsejos = new ListaConsejos();
		
	
    @GetMapping("/listado")
    public String getConsejos(Model model){
    	model.addAttribute("listaConsejos", listaConsejos.getListaConsejos());
        return "consejos";
    }
    
    @GetMapping("/nuevo-consejo")
 
    public String getnuevoConsejo(Model model) {
    	Consejo nuevoConsejo = new Consejo();    	
    	model.addAttribute("nuevoConsejo", nuevoConsejo);
    	return "nuevo-consejo";
    }
    
    @PostMapping("/nuevo-consejo")
    
    public ModelAndView crearConsejo(Consejo nuevoConsejo)
    {
    	ModelAndView modelAndView = new ModelAndView("consejos");
    	listaConsejos.getListaConsejos().add(nuevoConsejo);
    	modelAndView.addObject("listaConsejos",listaConsejos.getListaConsejos());
    	return modelAndView;
    	
    }
    
    
    

    
    
}
