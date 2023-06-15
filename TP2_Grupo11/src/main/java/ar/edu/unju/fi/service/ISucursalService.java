package ar.edu.unju.fi.service;

import ar.edu.unju.fi.entity.Sucursal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;

public interface ISucursalService {
    public String getSucursales(Model model);
    public String getNuevaSucursalPage(Model model);
    public ModelAndView crearSucursal(Sucursal formSucursal, BindingResult result);
    public String eliminarSucursal(Long id, Model model);
    public String editarSucursal(Long id, Model model);
    public ModelAndView modificarSucursal(Sucursal sucursalEditado, BindingResult result);
    public ModelAndView buscarSucursal(String query);
    public ModelAndView filtrarSucursal(LocalTime horaInicio, LocalTime horaFin);
}
