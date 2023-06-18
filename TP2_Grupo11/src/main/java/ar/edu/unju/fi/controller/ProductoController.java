package ar.edu.unju.fi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.entity.Producto;
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
    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result, @RequestParam("file") MultipartFile image) throws Exception{
        return productoService.crearProducto(formProducto, result, image);
    }
    
    @GetMapping("/eliminar-producto/{codigo}")
    public ModelAndView eliminarProducto(@PathVariable(value="codigo")Long codigo,Model model){
        return productoService.eliminarProducto(codigo,model);
    }
    
    @GetMapping("/editar-producto/{codigo}")
    public ModelAndView editarProducto(@PathVariable(value="codigo")Long codigo,Model model){
        return productoService.editarProducto(codigo,model);
    }
    
    @PostMapping("modificar-producto")
    public ModelAndView modificarLista(@Valid Producto modificado, BindingResult result, @RequestParam("file") MultipartFile image) throws Exception{
        return productoService.modificarLista(modificado, result,image);
    }

    @GetMapping("buscar-producto")
    public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado,@RequestParam("categoria") Long id, Model model){
        return productoService.buscar(buscado,id,model);
    }

    @GetMapping("uploads/{filename}")
    public ResponseEntity<Resource> cargarImagen(@PathVariable String filename){
        return productoService.cargarImagen(filename);
    }
    
}
   
