package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.Listas.ListaProductos;
import ar.edu.unju.fi.model.Producto;

@Controller
public class NuevoProductoController {
    ListaProductos listaProductos = new ListaProductos();
    @GetMapping("/nuevo_producto")
    public String getNuevoProductoPage(Model model){
        Producto formProducto = new Producto();
        model.addAttribute("formProducto", formProducto);
        return "nuevo_producto";
    }

    @PostMapping("/nuevo_producto")
    public String crearProducto(@ModelAttribute("formProducto")Producto formProducto, Model model){
        listaProductos.addProductos(formProducto);
        if (formProducto.validarProducto()){
            model.addAttribute("mensaje", "Se agrego correctamente");
            model.addAttribute("lista", listaProductos);
        }else{
            model.addAttribute("mensaje", "Error no se agrego el producto");
        }
        model.addAttribute("nuevoProducto", formProducto.toString());
        return "nuevo_producto";
    }


 

}

