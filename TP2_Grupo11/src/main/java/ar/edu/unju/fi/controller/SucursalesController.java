package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.Listas.ListaSucursal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sucursales")
public class SucursalesController {
    ListaSucursal listaSucursales = new ListaSucursal();

    @GetMapping("/listado")
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", listaSucursales.getListaSucursales());
        return "sucursales";
    }
}
