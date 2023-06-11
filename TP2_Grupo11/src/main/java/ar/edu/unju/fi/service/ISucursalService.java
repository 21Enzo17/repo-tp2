package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Sucursal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface ISucursalService {
    public String getSucursales(Model model);
    public String getNuevaSucursalPage(Model model);
    public ModelAndView crearSucursal(Sucursal formSucursal, BindingResult result);
    public String eliminarSucursal(String direccion, Model model);
    public String editarSucursal(String direccion, Model model);
    public ModelAndView modificarSucursal(Sucursal sucursalEditado, BindingResult result);
    public ModelAndView buscarSucursal(String query);
}