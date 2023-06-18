package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/datos")
public class GestionController {
    
    @GetMapping("/gestion")
    public String getProductos(Model model){
        return "gestion";
    }
}
