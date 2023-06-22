package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactoController {
    @GetMapping("/contactos")
    public String getContactos(){
        return "contactos";
    }
    
    @PostMapping("/formContactos")
    public String getNuevoContacto(){
    	return "contactos";
    	}
    
    public ModelAndView contactos(@Valid @ModelAttribute("formContactos") Contacto formContacto, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("contactos");
        }
        else{
            contactoService(formContacto);
            modelView = new ModelAndView("contactos");
            contactos.addObject("mensaje",true);
        }
        return modelView;
    }
    }
	
	

	
