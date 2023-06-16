package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.Listas.ListaSucursal;
import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.repository.SucursalRepository;
import ar.edu.unju.fi.service.IProvinciaService;
import ar.edu.unju.fi.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("sucursalServiceMysqlImp")
public class SucursalServiceMysqlImp implements ISucursalService {
    @Autowired
    private ListaSucursal listaSucursales;
    @Autowired
    private Sucursal sucursal;
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    IProvinciaService provinciaService;

    /**
     * Método que muestra la página de sucursales
     * @param model
     * @return sucursales.html
     */
    @Override
    public String getSucursales(Model model){
        model.addAttribute("listaSucursales", (List<Sucursal>) sucursalRepository.sucursalesDisponibles());
        model.addAttribute("sucursalesDisponibles", (List<Sucursal>) sucursalRepository.sucursalesDisponibles());
        return "sucursales";
    }

    /**
     * Método que muestra la página para crear una nueva sucursal
     * @param model
     * @return nueva-sucursal.html
     */
    @Override
    public String getNuevaSucursalPage(Model model){
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
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
    public ModelAndView crearSucursal(Sucursal formSucursal, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-sucursal");
            modelView.addObject("listaProvincias", provinciaService.obtenerProvincias());
            System.out.println("result = " + result.getFieldErrors().toString());
        }
        else{
            modelView = new ModelAndView("sucursales");
            sucursalRepository.save(formSucursal);
            modelView.addObject("listaSucursales", sucursalRepository.findAll());
        }
        return modelView;
    }

    /**
     * Método que elimina una sucursal de la lista según su dirección
     * @param id
     * @param model
     * @return sucursales.html
     */
    @Override
    public String eliminarSucursal(Long id, Model model){
        for(Sucursal sucursal:sucursalRepository.findAll()){
            if(sucursal.getId() == id){
                sucursal.setEstado(false);
                sucursalRepository.save(sucursal);
                break;
            }
        }
        model.addAttribute("listaSucursales", sucursalRepository.findAll());
        return "redirect:/sucursales/listado";
    }

    /**
     * Método que muestra la página para editar una sucursal
     * @param id
     * @param model
     * @return modificar-sucursal.html
     */
    @Override
    public String editarSucursal(Long id, Model model){
        model.addAttribute("sucursalEditar", sucursalRepository.findById(id).get());
        model.addAttribute("listaProvincias", provinciaService.obtenerProvincias());
        return "modificar-sucursal";
    }

    /**
     * Método que modifica una sucursal de la lista según su dirección
     * @param sucursalEditado
     * @param result
     * @return sucursales.html
     */
    @Override
    public ModelAndView modificarSucursal(Sucursal sucursalEditado, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("modificar-sucursal");
            modelView.addObject("listaProvincias", provinciaService.obtenerProvincias());
        }
        else{
            modelView = new ModelAndView("sucursales");
            for(Sucursal sucursal:sucursalRepository.findAll()){
                if(sucursal.getId() == sucursalEditado.getId()){
                    sucursal.setDireccion(sucursalEditado.getDireccion());
                    sucursal.setTelefono(sucursalEditado.getTelefono());
                    sucursal.setHorarioInicio(sucursalEditado.getHorarioInicio());
                    sucursal.setHorarioFin(sucursalEditado.getHorarioFin());
                    sucursal.setMail(sucursalEditado.getMail());
                    sucursalRepository.save(sucursalEditado);
                    break;
                }
            }
            modelView.addObject("listaSucursales", sucursalRepository.findAll());
        }
        return modelView;
    }
    /**
     * Método que busca una sucursal de la lista según su dirección
     * @param query
     * @return sucursales.html
     */
    @Override
    public ModelAndView buscarSucursal(String query){
        ModelAndView modelAndView = new ModelAndView("sucursales");
        List<Sucursal> queryResult = new ArrayList<>();
        for (Sucursal sucursal : sucursalRepository.sucursalesDisponibles()){
            if(sucursal.getDireccion().toLowerCase().contains(query.toLowerCase())){
                queryResult.add(sucursal);
            }
        }
        modelAndView.addObject("queryResult", queryResult);
        return modelAndView;
    }

    /**
     *
     * @param horaInicio
     * @param horaFin
     * @return sucursales.html
     */
    @Override
    public ModelAndView filtrarSucursal(@RequestParam LocalTime horaInicio, @RequestParam LocalTime horaFin){
        ModelAndView modelView = new ModelAndView("sucursales");
        List<Sucursal> queryResult = new ArrayList<>();
        for (Sucursal sucursal : sucursalRepository.sucursalesDisponibles()){
            if(sucursal.getHorarioInicio().isAfter(horaInicio) && sucursal.getHorarioFin().isBefore(horaFin)){
                queryResult.add(sucursal);
            }
        }
        modelView.addObject("queryResult", queryResult);
        sucursalRepository.findById(5L).get();
        return modelView;
    }
}
