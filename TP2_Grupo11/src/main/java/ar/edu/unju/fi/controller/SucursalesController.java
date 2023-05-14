package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SucursalesController {
    
    @GetMapping("/sucursales")
    public String getSucursales(){
        return "sucursales";
    }
}
