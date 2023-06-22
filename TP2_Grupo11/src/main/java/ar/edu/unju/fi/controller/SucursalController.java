package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.service.imp.ProvinciaServiceMysqlImp;
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

@Controller
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    @Qualifier("sucursalServiceMysqlImp")
    private SucursalServiceMysqlImp sucursalService;
    @Autowired
    private ProvinciaServiceMysqlImp provinciaService;

    /**
     * Método que muestra la página de sucursales
     * @param model
     * @return sucursales.html
     */
    @GetMapping("/listado")
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", sucursalService.getSucursales());
        model.addAttribute("sucursalesDisponibles", sucursalService.getSucursales());
        return "sucursales";
    }

    /**
     * Método que muestra la página para crear una nueva sucursal
     * @param model
     * @return nueva-sucursal.html
     */
    @GetMapping("/nueva-sucursal")
    public String getNuevaSucursalPage(Model model){
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
        model.addAttribute("formSucursal", sucursalService.getSucursal());
        return "nueva-sucursal";
    }

    /**
     * Método que crea una nueva sucursal y la agrega a la lista
     * @param formSucursal
     * @param result
     * @return sucursales.html
     */
    @PostMapping("/nueva-sucursal")
    public ModelAndView crearSucursal(@Valid @ModelAttribute("formSucursal") Sucursal formSucursal, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-sucursal");
            modelView.addObject("listaProvincias", provinciaService.obtenerProvincias());
        }
        else{
            modelView = new ModelAndView("sucursales");
            sucursalService.guardarSucursal(formSucursal);
            modelView.addObject("listaSucursales", sucursalService.getSucursales());
        }
        return modelView;
    }

    /**
     * Método que elimina una sucursal de la lista según su dirección
     * @param id
     * @param model
     * @return sucursales.html
     */
    @GetMapping("/eliminar-sucursal/{id}")
    public String eliminarSucursal(@PathVariable(value="id")Long id, Model model){
        sucursalService.eliminarSucursal(id);
        model.addAttribute("listaSucursales", sucursalService.getSucursales());
        return "redirect:/sucursales/listado";
    }

    /**
     * Método que muestra la página para editar una sucursal
     * @param id
     * @param model
     * @return modificar-sucursal.html
     */
    @GetMapping("/editar-sucursal/{id}")
    public String editarSucursal(@PathVariable(value="id")Long id, Model model){
        model.addAttribute("sucursalEditar", sucursalService.getSucursalById(id));
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
        return "modificar-sucursal";
    }

    /**
     * Método que modifica una sucursal de la lista según su dirección
     * @param sucursalEditado
     * @param result
     * @return sucursales.html
     */
    @PostMapping("/editar-sucursal")
    public ModelAndView modificarSucursal(@Valid @ModelAttribute("sucursalEditar") Sucursal sucursalEditado, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("modificar-sucursal");
            modelView.addObject("listaProvincias", provinciaService.obtenerProvincias());
        }
        else{
            modelView = new ModelAndView("sucursales");
            sucursalService.modificarSucursal(sucursalEditado);
            modelView.addObject("listaSucursales", sucursalService.getSucursales());
        }
        return modelView;
    }

    /**
     * Método que busca una sucursal de la lista según su dirección
     * @param query
     * @return sucursales.html
     */
    @GetMapping("/buscar-sucursal")
    public ModelAndView buscarSucursal(@RequestParam String query){
        ModelAndView modelAndView = new ModelAndView("sucursales");
        modelAndView.addObject("queryResult", sucursalService.getSucursalByDireccion(query));
        return modelAndView;
    }

    /**
     *
     * @param horaInicio
     * @param horaFin
     * @return sucursales.html
     */
    @GetMapping("/filtrar-horario")
    public ModelAndView filtrarSucursal(@RequestParam LocalTime horaInicio, @RequestParam LocalTime horaFin){
        ModelAndView modelView = new ModelAndView("sucursales");
        modelView.addObject("queryResult", sucursalService.filtrarSucursal(horaInicio, horaFin));
        return modelView;
    }
}
