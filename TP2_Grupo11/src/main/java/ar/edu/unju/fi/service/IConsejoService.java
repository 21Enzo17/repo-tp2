package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Consejo;
import jakarta.validation.Valid;

public interface IConsejoService {
	
    List <Consejo> getConsejos();

    List <Consejo> getnuevoConsejo();

    List <Consejo> crearConsejo(@Valid @ModelAttribute("nuevoConsejo")Consejo nuevoConsejo, BindingResult result);

    List <Consejo> eliminarConsejo();

    List <Consejo> editarConsejos(@PathVariable(value="id")int id,Model model);

    List <Consejo> modificarLista(@Valid @ModelAttribute("consejosEditar")Consejo modificado, BindingResult result);

    List <Consejo> buscarPorTitulo(@RequestParam("titulo") String buscado, Model model);

}

