package ar.edu.unju.fi.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Consejo;
import jakarta.validation.Valid;

public interface IConsejoService {
	
	
    public ModelAndView getConsejos(Model model);

    public ModelAndView getnuevoConsejo(Model model);

    public ModelAndView crearConsejo(@Valid @ModelAttribute("nuevoConsejo")Consejo nuevoConsejo, BindingResult result);

    public ModelAndView eliminarConsejo(@PathVariable(value="id")int id,Model model);

    public ModelAndView editarConsejos(@PathVariable(value="id")int id,Model model);

    public ModelAndView modificarLista(@Valid @ModelAttribute("consejosEditar")Consejo modificado, BindingResult result);

    public ModelAndView buscarPorTitulo(@RequestParam("titulo") String buscado, Model model);
}

