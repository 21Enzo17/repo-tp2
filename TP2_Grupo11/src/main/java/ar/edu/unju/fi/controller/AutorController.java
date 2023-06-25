package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.service.IAutorService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.entity.Autor;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
    private IAutorService autorService;
    @Autowired
    private Autor autor;
    
    @GetMapping("/listado")
    public String listaCategorias(Model model){
        model.addAttribute("autores", autorService.getDisponibles());
        return "autor";
        }
    
    @GetMapping("/nueva")
    public String nuevaCategoria(Model model){
        autor.setEstado(true);
        model.addAttribute("nuevo", autor);
        model.addAttribute("autores", autorService.getDisponibles());
        return "nuevo-autor";
    }
    
    @PostMapping("/guardar")
    public ModelAndView guardarAutor(@Valid @ModelAttribute("nuevo")Autor autor, BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("nuevo-autor");
        }else{
            modelView = new ModelAndView("autor");
            autor.setEstado(true);
            autorService.addAutor(autor);
            modelView.addObject("autores",autorService.getDisponibles());
            autor.toString();
        }
        return modelView;
    }
    @GetMapping("/eliminar-autor/{id}")
    public String eliminarAutor(@PathVariable(value="id")Long id,Model model){
        autorService.eliminarAutor(autorService.findAutorById(id));
        model.addAttribute("autores", autorService.getDisponibles());
        return "autor";
    }
    
    @GetMapping("/editar-autor/{id}")
    public String editarAutor(@PathVariable(value="id")Long id, Model model){
        model.addAttribute("encontrado", autorService.findAutorById(id));
        return "modificar-autor";
    }
    
    @PostMapping("/editar-autor/guardar")
    public ModelAndView guardarAutorEditado(@Valid @ModelAttribute("encontrado")Autor autor,BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-autor");
        }else{
            autor.setEstado(true);
            autorService.addAutor(autor);
            modelView = new ModelAndView("autor");
            modelView.addObject("autores",autorService.getDisponibles());
            
        }
        return modelView;
    }

}