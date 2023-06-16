package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.service.IProvinciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {
    @Autowired
    IProvinciaService provinciaService;

    @GetMapping("/listado")
    public String getListaProvinciasPage(Model model){
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
        return "provincias";
    }
    @GetMapping("/nueva-provincia")
    public String getNuevaProvinciaPage(Model model){
        return provinciaService.getNuevaProvinciaPage(model);
    }

    @PostMapping("/guardar-provincia")
    public ModelAndView guardarProvincia(@Valid @ModelAttribute("formProvincia") Provincia formProvincia, BindingResult result){
        return provinciaService.guardarProvincia(formProvincia, result);
    }
    @GetMapping("/eliminar-provincia/{id}")
    public String eliminarProvincia(@PathVariable Long id){
        return provinciaService.eliminarProvincia(id);
    }
    @GetMapping("/editar-provincia/{id}")
    public String editarProvincia(@PathVariable Long id, Model model){
        return provinciaService.editarProvincia(id, model);
    }
    @PostMapping("/modificar-provincia")
    public ModelAndView modificarProvincia(@Valid @ModelAttribute("provinciaEditar") Provincia provinciaEditado, BindingResult result){
        return provinciaService.modificarProvincia(provinciaEditado, result);
    }

}
