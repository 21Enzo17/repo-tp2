package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.Listas.ListaProductos;
import ar.edu.unju.fi.model.Producto;
@Controller
@RequestMapping("/producto")
public class ProductosController {
    ListaProductos listaProductos = new ListaProductos();
    // String nombre, int cod, float precio, String categoria, int descuento, String imagen
    
    @GetMapping("/listado")
    public String getProductos(Model model){
        model.addAttribute("listaProductos", listaProductos.getProductos());
        return "/producto";
    }

    @GetMapping("/nuevo-producto")
    public String getNuevoProductoPage(Model model){
        Producto formProducto = new Producto();
        model.addAttribute("formProducto", formProducto);
        return "nuevo-producto";
    }

    @PostMapping("/nuevo-producto")
    public String crearProducto(@ModelAttribute("formProducto")Producto formProducto, Model model){
        listaProductos.addProductos(formProducto);
        if (formProducto.validarProducto()){
            model.addAttribute("mensaje", "Se agrego correctamente");
            model.addAttribute("lista", listaProductos);
            return "redirect:/producto/listado";
        }
        model.addAttribute("mensaje", "Error no se agrego el producto");
        return "/nuevo-producto";
    }

    @GetMapping("/eliminar-producto/{codigo}")
    public String eliminarProducto(@PathVariable(value="codigo")int codigo,Model model){
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                listaProductos.getProductos().remove(producto);
                break;
            }
        }
        model.addAttribute("listaProductos", listaProductos.getProductos());
        return "redirect:/producto/listado";
    }
}
