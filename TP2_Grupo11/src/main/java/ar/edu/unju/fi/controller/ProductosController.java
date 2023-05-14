package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductosController {
    
    @GetMapping("/producto")
    public String getProductos(){
        return "producto";
    }
}
