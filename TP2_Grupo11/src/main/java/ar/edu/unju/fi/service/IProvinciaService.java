package ar.edu.unju.fi.service;

import ar.edu.unju.fi.entity.Provincia;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface IProvinciaService {
    public String getNuevaProvinciaPage(Model model);
    public ModelAndView guardarProvincia(Provincia formProvincia, BindingResult result);
    public String eliminarProvincia(Long id);
    public String editarProvincia(Long id, Model model);
public ModelAndView modificarProvincia(Provincia provinciaEditado, BindingResult result);
    public List<Provincia> obtenerProvincias();


}
