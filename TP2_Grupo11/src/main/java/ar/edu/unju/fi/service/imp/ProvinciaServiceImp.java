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
public class ProvinciaServiceImp implements IProvinciaService {
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public String getNuevaProvinciaPage(Model model){
        model.addAttribute("formProvincia", new Provincia());
        return "nueva-provincia";
    }

    @Override
    public ModelAndView guardarProvincia(Provincia formProvincia, BindingResult result){
        ModelAndView modelView;
        if (result.hasErrors()){
            modelView = new ModelAndView("nueva-provincia");
        }
        else{
            provinciaRepository.save(formProvincia);
            modelView = new ModelAndView("gestion");
        }
        return modelView;
    }

    @Override
    public List<Provincia> obtenerProvincias(){
        return (List<Provincia>) provinciaRepository.findAll();
    }

}
