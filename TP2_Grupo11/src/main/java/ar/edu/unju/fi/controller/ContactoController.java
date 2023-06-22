package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Contacto;
import ar.edu.unju.fi.service.IContactoService;
import jakarta.validation.Valid;

@Controller
public class ContactoController {
	@Autowired
	IContactoService contactoService;
    @GetMapping("/contactos")
    public String getContactos(Model model){
    	model.addAttribute("formContactos", new Contacto());
        return "contactos";
    }
    
    @PostMapping("/formContacto")
    public ModelAndView contacto(@Valid @ModelAttribute("formContactos") Contacto formContacto, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("contactos");
        }
        else{
            contactoService.crearContacto(formContacto);
            modelView = new ModelAndView("contactos");
            modelView.addObject("alerta",true);
        }
        return modelView;
    }
    }
	
	

	
