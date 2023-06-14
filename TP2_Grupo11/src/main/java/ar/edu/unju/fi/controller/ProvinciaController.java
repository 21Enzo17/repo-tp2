package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.service.IProvinciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {
    @Autowired
    IProvinciaService provinciaService;
    @GetMapping("/nueva-provincia")
    public String getNuevaProvinciaPage(Model model){
        return provinciaService.getNuevaProvinciaPage(model);
    }

    @PostMapping("/guardar-provincia")
    public ModelAndView guardarProvincia(@Valid @ModelAttribute("formProvincia") Provincia formProvincia, BindingResult result){
        return provinciaService.guardarProvincia(formProvincia, result);
    }
}
