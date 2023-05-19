package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.Listas.ListaSucursal;
import ar.edu.unju.fi.model.Sucursal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sucursales")
public class SucursalesController {
    ListaSucursal listaSucursales = new ListaSucursal();

    @GetMapping("/listado")
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", listaSucursales.getListaSucursales());
        return "sucursales";
    }

    @GetMapping("/nueva-sucursal")
    public String getNuevaSucursalPage(Model model){
        Sucursal formSucursal = new Sucursal();
        model.addAttribute("formSucursal", formSucursal);
        return "nueva-sucursal";
    }

    @PostMapping("/nueva-sucursal")
    public ModelAndView crearSucursal(Sucursal formSucursal){
        ModelAndView modelView = new ModelAndView("sucursales");
        listaSucursales.getListaSucursales().add(formSucursal);
        modelView.addObject("listaSucursales", listaSucursales.getListaSucursales());
        return modelView;
    }

    
}
