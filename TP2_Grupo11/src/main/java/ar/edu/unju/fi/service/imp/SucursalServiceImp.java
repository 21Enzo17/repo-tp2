package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.Listas.ListaSucursal;
import ar.edu.unju.fi.model.Sucursal;
import ar.edu.unju.fi.service.ISucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Service
public class SucursalServiceImp implements ISucursalService {
    @Autowired
    private ListaSucursal listaSucursales;
    @Autowired
    private Sucursal sucursal;

    /**
     * Método que muestra la página de sucursales
     * @param model
     * @return sucursales.html
     */
    @Override
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", listaSucursales.getListaSucursales());
        return "sucursales";
    }

    /**
     * Método que muestra la página para crear una nueva sucursal
     * @param model
     * @return nueva-sucursal.html
     */
    @Override
    public String getNuevaSucursalPage(Model model){
        model.addAttribute("formSucursal", sucursal);
        return "nueva-sucursal";
    }

    /**
     * Método que crea una nueva sucursal y la agrega a la lista
     * @param formSucursal
     * @param result
     * @return sucursales.html
     */
    @Override
    public ModelAndView crearSucursal(@Valid @ModelAttribute("formSucursal") Sucursal formSucursal, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-sucursal");

        }
        else{
            modelView = new ModelAndView("sucursales");
            listaSucursales.getListaSucursales().add(formSucursal);
            modelView.addObject("listaSucursales", listaSucursales.getListaSucursales());
        }
        return modelView;
    }

    /**
     * Método que elimina una sucursal de la lista según su dirección
     * @param direccion
     * @param model
     * @return sucursales.html
     */
    @Override
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
    @Override
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
     * @param result
     * @return sucursales.html
     */
    @Override
    public ModelAndView modificarSucursal(@Valid @ModelAttribute("sucursalEditar") Sucursal sucursalEditado, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("modificar-sucursal");
        }
        else{
            modelView = new ModelAndView("sucursales");
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
        }
        return modelView;
    }
}
