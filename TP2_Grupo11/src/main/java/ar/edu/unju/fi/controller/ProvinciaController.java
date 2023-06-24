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

    /**
     * Método que muestra la página de provincias
     * @param model
     * @return provincias.html
     */
    @GetMapping("/listado")
    public String getListaProvinciasPage(Model model){
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
        return "provincias";
    }

    @GetMapping("/nueva-provincia")
    public String getNuevaProvinciaPage(Model model){
        model.addAttribute("formProvincia", provinciaService.getProvincia());
        return "nueva-provincia";
    }

    /**
     * Método que crea una nueva provincia y la agrega a la base de datos.
     * @param formProvincia
     * @param result
     * @return provincias.html
     */
    @PostMapping("/guardar-provincia")
    public ModelAndView guardarProvincia(@Valid @ModelAttribute("formProvincia") Provincia formProvincia, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-provincia");
        }
        else{
            provinciaService.guardarProvincia(formProvincia);
            modelView = new ModelAndView("provincias");
        }
        return modelView;
    }

    /**
     * Método que elimina lógicamente una provincia de la base de datos.
     * @param id
     * @return provincias.html
     */
    @GetMapping("/eliminar-provincia/{id}")
    public String eliminarProvincia(@PathVariable Long id){
        provinciaService.eliminarProvincia(id);
        return "redirect:/provincia/listado";
    }

    /**
     * Método que muestra la página para modificar una provincia.
     * @param id
     * @param model
     * @return modificar-provincia.html
     */
    @GetMapping("/editar-provincia/{id}")
    public String editarProvincia(@PathVariable Long id, Model model){
        model.addAttribute("provinciaEditar", provinciaService.getProvinciaById(id));
        return "modificar-provincia";
    }

    /**
     * Método que modifica una provincia y la actualiza en la base de datos.
     * @param provinciaEditado
     * @param result
     * @return provincias.html
     */
    @PostMapping("/modificar-provincia")
    public ModelAndView modificarProvincia(@Valid @ModelAttribute("provinciaEditar") Provincia provinciaEditado, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("modificar-provincia");
        }
        else{
            provinciaService.modificarProvincia(provinciaEditado);
            modelView = new ModelAndView("provincias");
        }
        return modelView;
    }

}
