package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.model.Sucursal;
import ar.edu.unju.fi.service.imp.SucursalServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    private SucursalServiceImp sucursalService;

    @GetMapping("/listado")
    public String getSucursales(Model model){
        return sucursalService.getSucursales(model);
    }

    @GetMapping("/nueva-sucursal")
    public String getNuevaSucursalPage(Model model){
        return sucursalService.getNuevaSucursalPage(model);
    }

    @PostMapping("/nueva-sucursal")
    public ModelAndView crearSucursal(@Valid @ModelAttribute("formSucursal") Sucursal formSucursal, BindingResult result){
        return sucursalService.crearSucursal(formSucursal, result);
    }

    @GetMapping("/eliminar-sucursal/{direccion}")
    public String eliminarSucursal(@PathVariable(value="direccion")String direccion, Model model){
        return sucursalService.eliminarSucursal(direccion, model);
    }

    @GetMapping("/editar-sucursal/{direccion}")
    public String editarSucursal(@PathVariable(value="direccion")String direccion, Model model){
        return sucursalService.editarSucursal(direccion, model);
    }

    @PostMapping("/editar-sucursal")
    public ModelAndView modificarSucursal(@Valid @ModelAttribute("sucursalEditar") Sucursal sucursalEditado, BindingResult result){
        return sucursalService.modificarSucursal(sucursalEditado, result);
    }
}
