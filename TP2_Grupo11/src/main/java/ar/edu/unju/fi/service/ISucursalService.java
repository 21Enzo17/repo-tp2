package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Sucursal;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public interface ISucursalService {
    public String getSucursales(Model model);
    public String getNuevaSucursalPage(Model model);
    public ModelAndView crearSucursal(@Valid @ModelAttribute("formSucursal") Sucursal formSucursal, BindingResult result);
    public String eliminarSucursal(@PathVariable(value="direccion")String direccion, Model model);
    public String editarSucursal(@PathVariable(value="direccion")String direccion, Model model);
    public ModelAndView modificarSucursal(@Valid @ModelAttribute("sucursalEditar") Sucursal sucursalEditado, BindingResult result);
}
