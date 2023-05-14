package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class NuevoProductoController {
    @GetMapping("/nuevo_producto")
    public String getNuevoProductoPage(){
        return "nuevo_producto";
    }
}

