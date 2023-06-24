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

import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.service.ICategoriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private Categoria categoria;

    @GetMapping("/listado")
    public String listaCategorias(Model model){
        model.addAttribute("categorias", categoriaService.getDisponibles());
        return "categoria";
    }

    @GetMapping("/nueva")
    public String nuevaCategoria(Model model){
        categoria.setEstado(true);
        model.addAttribute("nuevo", categoria);
        return "nueva-categoria";
    }

    @PostMapping("/guardar")
    public ModelAndView guardarCategoria(@Valid @ModelAttribute("nuevo")Categoria categoria, BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("nueva-categoria");
        }else{
            modelView = new ModelAndView("categoria");
            categoria.setEstado(true);
            categoriaService.addCategoria(categoria);
            modelView.addObject("categorias",categoriaService.getDisponibles());
        }
        return modelView;
    }

    @GetMapping("/eliminar-categoria/{id}")
    public String eliminarCategoria(@PathVariable(value="id")Long id,Model model){
        categoriaService.eliminarCategoria(categoriaService.findCategoriaById(id));
        model.addAttribute("categorias", categoriaService.getDisponibles());
        return "categoria";
    }

    @GetMapping("/editar-categoria/{id}")
    public String editarCategoria(@PathVariable(value="id")Long id, Model model){
        model.addAttribute("encontrado", categoriaService.findCategoriaById(id));
        return "modificar-categoria";
    }

    @PostMapping("/editar-categoria/guardar")
    public ModelAndView guardarCategoriaEditada(@Valid @ModelAttribute("encontrado")Categoria categoria,BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-categoria");
        }else{
            categoria.setEstado(true);
            categoriaService.addCategoria(categoria);
            modelView = new ModelAndView("categoria");
            modelView.addObject("categorias",categoriaService.getDisponibles());
            
        }
        return modelView;
    }
    
}
