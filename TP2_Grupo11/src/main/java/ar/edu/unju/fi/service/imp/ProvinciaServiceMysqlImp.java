package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.repository.ProvinciaRepository;
import ar.edu.unju.fi.service.IProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProvinciaServiceMysqlImp implements IProvinciaService {
    @Autowired
    ProvinciaRepository provinciaRepository;

    /**
     * Método que muestra la página de provincias
     * @param model
     * @return provincias.html
     */
    @Override
    public String getNuevaProvinciaPage(Model model){
        model.addAttribute("formProvincia", new Provincia());
        return "nueva-provincia";
    }

    /**
     * Método que crea una nueva provincia y la agrega a la base de datos.
     * @param formProvincia
     * @param result
     * @return provincias.html
     */
    @Override
    public ModelAndView guardarProvincia(Provincia formProvincia, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-provincia");
        }
        else{
            provinciaRepository.save(formProvincia);
            modelView = new ModelAndView("provincias");
        }
        return modelView;
    }

    /**
     * Método que elimina lógicamente una provincia de la base de datos.
     * @param id
     * @return provincias.html
     */
    @Override
    public String eliminarProvincia(Long id) {
        Provincia prov = provinciaRepository.findById(id).get();
        prov.setEstado(false);
        System.out.println("prov = " + prov.toString());
        provinciaRepository.save(prov);
        return "redirect:/provincia/listado";
    }

    /**
     * Método que muestra la página para modificar una provincia.
     * @param id
     * @param model
     * @return modificar-provincia.html
     */
    @Override
    public String editarProvincia(Long id, Model model) {
        model.addAttribute("provinciaEditar", provinciaRepository.findById(id));
        return "modificar-provincia";
    }

    /**
     * Método que modifica una provincia y la actualiza en la base de datos.
     * @param provinciaEditado
     * @param result
     * @return provincias.html
     */
    @Override
    public ModelAndView modificarProvincia(Provincia provinciaEditado, BindingResult result) {
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("modificar-provincia");
        }
        else{
            provinciaRepository.save(provinciaEditado);
            modelView = new ModelAndView("provincias");
        }
        return modelView;
    }

    /**
     * Método que devuelve una lista de provincias disponibles (con estado en true).
     * @return List<Provincia>
     */

    @Override
    public List<Provincia> obtenerProvincias(){
        return (List<Provincia>) provinciaRepository.provinciasDisponibles();
    }

}
