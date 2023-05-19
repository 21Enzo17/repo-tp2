package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView crearProducto(@ModelAttribute("formProducto")Producto formProducto){
        ModelAndView modelView = new ModelAndView("producto");
        listaProductos.getProductos().add(formProducto);
        modelView.addObject("listaProductos", listaProductos.getProductos());
        return modelView;
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
    @GetMapping("/editar-producto/{codigo}")
    public String editarProducto(@PathVariable(value="codigo")int codigo,Model model){
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                model.addAttribute("encontrado", producto);
                System.out.println(producto.toString());
                break;
            }
        }
        return "modificar-producto";
    }
    @PostMapping("modificar-producto")
    public String modificarLista(@ModelAttribute("encontrado")Producto modificado){
        for(Producto producto:listaProductos.getProductos()){
            if(modificado.getCod()==producto.getCod()){
                producto.setNombre(modificado.getNombre());
                producto.setCategoria(modificado.getCategoria());
                producto.setDescuento(modificado.getDescuento());
                producto.setImagen(modificado.getImagen());
                producto.setPrecio(modificado.getPrecio());
                break;
            }
        }
        return "redirect:/producto/listado";
    }

}
