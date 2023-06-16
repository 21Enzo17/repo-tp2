package ar.edu.unju.fi.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Producto;
import jakarta.validation.Valid;

public interface IProductoService{
    
    public ModelAndView getProductos(Model model);
    
    public ModelAndView getNuevoProductoPage(Model model);

    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result);

    public ModelAndView eliminarProducto(@PathVariable(value="codigo")int codigo,Model model);

    public ModelAndView editarProducto(@PathVariable(value="codigo")int codigo,Model model);

    public ModelAndView modificarLista(@Valid @ModelAttribute("encontrado")Producto modificado, BindingResult result);

    public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado, Model model);
}
