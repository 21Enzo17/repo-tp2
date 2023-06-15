package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.service.imp.SucursalServiceImp;
import ar.edu.unju.fi.service.imp.SucursalServiceMysqlImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Timer;

@Controller
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    @Qualifier("sucursalServiceMysqlImp")
    private SucursalServiceMysqlImp sucursalService;

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

    @GetMapping("/eliminar-sucursal/{id}")
    public String eliminarSucursal(@PathVariable(value="id")Long id, Model model){
        return sucursalService.eliminarSucursal(id, model);
    }

    @GetMapping("/editar-sucursal/{id}")
    public String editarSucursal(@PathVariable(value="id")Long id, Model model){
        return sucursalService.editarSucursal(id, model);
    }

    @PostMapping("/editar-sucursal")
    public ModelAndView modificarSucursal(@Valid @ModelAttribute("sucursalEditar") Sucursal sucursalEditado, BindingResult result){
        return sucursalService.modificarSucursal(sucursalEditado, result);
    }

    @GetMapping("/buscar-sucursal")
    public ModelAndView buscarSucursal(@RequestParam String query){
        return sucursalService.buscarSucursal(query);
    }

    @GetMapping("/filtrar-horario")
    public ModelAndView filtrarSucursal(@RequestParam LocalTime horaInicio, @RequestParam LocalTime horaFin){
        return sucursalService.filtrarSucursal(horaInicio, horaFin);
    }
}
