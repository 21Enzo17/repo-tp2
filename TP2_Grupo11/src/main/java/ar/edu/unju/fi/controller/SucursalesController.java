package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.Listas.ListaSucursal;
import ar.edu.unju.fi.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ListaSucursal listaSucursales;

    /**
     * Método que muestra la página de sucursales
     * @param model
     * @return sucursales.html
     */
    @GetMapping("/listado")
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", listaSucursales.getListaSucursales());
        return "sucursales";
    }
 
    /**
     * Método que muestra la página para crear una nueva sucursal
     * @param model
     * @return nueva-sucursal.html
     */
    @GetMapping("/nueva-sucursal")
    public String getNuevaSucursalPage(Model model){
        Sucursal formSucursal = new Sucursal();
        model.addAttribute("formSucursal", formSucursal);
        return "nueva-sucursal";
    }

    /**
     * Método que crea una nueva sucursal y la agrega a la lista
     * @param formSucursal
     * @return sucursales.html
     */
    @PostMapping("/nueva-sucursal")
    public ModelAndView crearSucursal(Sucursal formSucursal){
        ModelAndView modelView = new ModelAndView("sucursales");
        listaSucursales.getListaSucursales().add(formSucursal);
        modelView.addObject("listaSucursales", listaSucursales.getListaSucursales());
        return modelView;
    }

    /**
     * Método que elimina una sucursal de la lista según su dirección
     * @param direccion
     * @param model
     * @return sucursales.html
     */
    @GetMapping("/eliminar-sucursal/{direccion}")
    public String eliminarSucursal(@PathVariable(value="direccion")String direccion, Model model){
        for(Sucursal sucursal:listaSucursales.getListaSucursales()){
            if(sucursal.getDireccion().equals(direccion)){
                listaSucursales.getListaSucursales().remove(sucursal);
                break;
            }
        }
        model.addAttribute("listaSucursales", listaSucursales.getListaSucursales());
        return "redirect:/sucursales/listado";
    }

    /**
     * Método que muestra la página para editar una sucursal
     * @param direccion
     * @param model
     * @return modificar-sucursal.html
     */
    @GetMapping("/editar-sucursal/{direccion}")
    public String editarSucursal(@PathVariable(value="direccion")String direccion, Model model){
        for(Sucursal sucursal:listaSucursales.getListaSucursales()){
            if(sucursal.getDireccion().equals(direccion)){
                model.addAttribute("sucursalEditar", sucursal);
                break;
            }
        }
        return "modificar-sucursal";
    }

    /**
     * Método que modifica una sucursal de la lista según su dirección
     * @param sucursalEditado
     * @return sucursales.html
     */
    @PostMapping("/editar-sucursal")
    public ModelAndView modificarSucursal(Sucursal sucursalEditado){
        ModelAndView modelView = new ModelAndView("sucursales");
        for(Sucursal sucursal:listaSucursales.getListaSucursales()){
            if(sucursal.getDireccion().equals(sucursalEditado.getDireccion())){
                sucursal.setDireccion(sucursalEditado.getDireccion());
                sucursal.setTelefono(sucursalEditado.getTelefono());
                sucursal.setHorarioAtencion(sucursalEditado.getHorarioAtencion());
                sucursal.setMail(sucursalEditado.getMail());
                break;
            }
        }
        modelView.addObject("listaSucursales", listaSucursales.getListaSucursales());
        return modelView;
    }
}
