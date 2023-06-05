package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.Listas.ListaProducto;
import ar.edu.unju.fi.model.Producto;
import jakarta.validation.Valid;
/**
 * Esta clase es la clase controladora de la pagina productos
 * @author: Grupo 11
 * @version: 21/05/2023
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ListaProducto listaProductos;

    @Autowired
    private Producto formProducto;


    /**
     * Metodo que muestra la pagina de productos
     * @param model
     * @return producto.html

     */
    @GetMapping("/listado")
    public String getProductos(Model model){
        model.addAttribute("listaProductos", listaProductos.getProductos());
        return "/producto";
    }
    /**
     * Metodo que muestra la pagina de agregar producto
     * @param model
     * @return nuevo-producto.html
     */
    @GetMapping("/nuevo-producto")
    public String getNuevoProductoPage(Model model){
        model.addAttribute("formProducto", formProducto);
        return "nuevo-producto";
    }
    /**
     * Metodo que crea un producto y lo agrega a la lista
     * @param formProducto
     * @return  nuevo-producto.html
     */
    @PostMapping("/nuevo-producto")
    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("nuevo-producto");
        }else{
            modelView = new ModelAndView("producto");
            listaProductos.getProductos().add(formProducto);
            modelView.addObject("listaProductos", listaProductos.getProductos());
        }
        return modelView;
    }
    /**
     * Metodo que elimina un producto de la lista
     * @param codigo
     * @param model
     * @return producto.html
     */
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
    /**
     * Metodo que permite editar un producto
     * @param codigo
     * @param model
     * @return modificar-producto.html
     */
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
    /**
     * Metodo que modifica el producto
     * @param modificado
     * @return producto.html
     */
    @PostMapping("modificar-producto")
    public ModelAndView modificarLista(@Valid @ModelAttribute("encontrado")Producto modificado, BindingResult result){
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-producto");
        }else{
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
            modelView = new ModelAndView("producto");
            modelView.addObject("listaProductos", listaProductos.getProductos());
        }
        return modelView;
    }
    }
