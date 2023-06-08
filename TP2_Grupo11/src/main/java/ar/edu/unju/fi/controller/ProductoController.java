package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.model.Producto;
import ar.edu.unju.fi.service.IProductoService;
import jakarta.validation.Valid;
/**
 * Esta clase es la clase controladora de la pagina productos
 * @author: Grupo 11
 * @version: 08/06/2023
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    IProductoService productoService;

    @GetMapping("/listado")
    public ModelAndView getProductos(Model model){
        return productoService.getProductos(model);
    }
    
    @GetMapping("/nuevo-producto")
    public ModelAndView getNuevoProductoPage(Model model){
        /*model.addAttribute("formProducto", formProducto);*/
        return productoService.getNuevoProductoPage(model);
    }
    
    @PostMapping("/nuevo-producto")
    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result){
        return productoService.crearProducto(formProducto, result);
    }
    
    @GetMapping("/eliminar-producto/{codigo}")
    public ModelAndView eliminarProducto(@PathVariable(value="codigo")int codigo,Model model){
        return productoService.eliminarProducto(codigo,model);
    }
    
    @GetMapping("/editar-producto/{codigo}")
    public ModelAndView editarProducto(@PathVariable(value="codigo")int codigo,Model model){
        return productoService.editarProducto(codigo,model);
    }
    
    @PostMapping("modificar-producto")
    public ModelAndView modificarLista(@Valid @ModelAttribute("encontrado")Producto modificado, BindingResult result){
        return productoService.modificarLista(modificado, result);
    }

    @GetMapping("buscar-producto")
    public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado, Model model){
        return productoService.buscarPorNombre(buscado,model);
    }
    }
