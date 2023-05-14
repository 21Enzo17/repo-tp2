package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    
    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/producto")
    public String getProductos(){
        return "producto";
    }

    @GetMapping("/paseos")
    public String getPaseos(){
        return "paseos";
    }

    @GetMapping("/sucursales")
    public String getSucursales(){
        return "sucursales";
    }

    @GetMapping("/contactos")
    public String getContactos(){
        return "contactos";
    }

    @GetMapping("/consejos")
    public String getConsejos(){
        return "consejos";
    }
}
