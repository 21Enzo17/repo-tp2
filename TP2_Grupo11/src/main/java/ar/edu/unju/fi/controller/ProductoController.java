package ar.edu.unju.fi.controller;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import ar.edu.unju.fi.service.ICategoriaService;
import ar.edu.unju.fi.service.IProductoService;
import ar.edu.unju.fi.util.UploadFile;
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
    private IProductoService productoService;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private Producto formProducto;
    @Autowired
    private UploadFile uploadFile;

    /**
     * Metodo que muestra la pagina de productos
     * @param model
     * @return producto.html
     */
    @GetMapping("/listado")
    public String getProductos(Model model){
        model.addAttribute("buscado");
        model.addAttribute("listaProductos",productoService.getDisponibles());
        model.addAttribute("listaCategorias",categoriaService.getDisponibles());
        return "producto";
    }
    
    /**
     * Metodo que muestra la pagina de agregar producto
     * @param model
     * @return nuevo-producto.html
     */
    @GetMapping("/nuevo-producto")
    public String getNuevoProductoPage(Model model){
        model.addAttribute("listaCategorias", categoriaService.getDisponibles());
        model.addAttribute("formProducto",formProducto);
        return "nuevo-producto";
    }
    
    /**
     * Metodo que crea un producto y lo agrega a la lista
     * @param formProducto
     * @return  nuevo-producto.html
     */
    @PostMapping("/nuevo-producto")
    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result, @RequestParam("file") MultipartFile image) throws Exception{
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("nuevo-producto");
            modelView.addObject("listaCategorias",categoriaService.getDisponibles());
        }else{
            modelView = new ModelAndView("producto");
            String uniqueFileName = uploadFile.copy(image);
            formProducto.setImagen(uniqueFileName);
            productoService.addProducto(formProducto);
            modelView.addObject("listaProductos", productoService.getDisponibles());
            modelView.addObject("listaCategorias",categoriaService.getDisponibles());
        }
        return modelView;
    }
    
    /**
     * Metodo que elimina un producto de la lista
     * @param id
     * @param model
     * @return producto.html
     */
    @GetMapping("/eliminar-producto/{codigo}")
    public String eliminarProducto(@PathVariable(value="codigo")Long codigo,Model model){
        Producto producto = productoService.findProductoById(codigo);
        productoService.eliminarProducto(producto);
        model.addAttribute("listaProductos", productoService.getDisponibles());
        model.addAttribute("listaCategorias", categoriaService.getDisponibles());
        return "producto";
    }
    
    /**
     * Metodo que permite editar un producto
     * @param id
     * @param model
     * @return modificar-producto.html
     */
    @GetMapping("/editar-producto/{codigo}")
    public String editarProducto(@PathVariable(value="codigo")Long codigo,Model model){
        model.addAttribute("listaCategorias",categoriaService.getDisponibles());
        model.addAttribute("encontrado", productoService.findProductoById(codigo));
        return "modificar-producto";
    }
    
    /**
     * Metodo que modifica el producto
     * @param modificado
     * @return producto.html
     */
    @PostMapping("modificar-producto")
    public ModelAndView modificarLista(@Valid Producto modificado, BindingResult result, @RequestParam("file") MultipartFile image) throws Exception{
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-producto");
        }else{
            Producto producto = productoService.findProductoById(modificado.getId());
            producto.setNombre(modificado.getNombre());
            producto.setCategoria(modificado.getCategoria());
            producto.setDescuento(modificado.getDescuento());
            if(!image.isEmpty()){
                uploadFile.delete(producto.getImagen());
                String uniqueFileName = uploadFile.copy(image);
                producto.setImagen(uniqueFileName);
            }
            producto.setPrecio(modificado.getPrecio());
            productoService.addProducto(producto);
            modelView = new ModelAndView("producto");
            modelView.addObject("listaProductos", productoService.getDisponibles());
            modelView.addObject("listaCategorias",categoriaService.getDisponibles());
        }
        return modelView;
    }

    /**
     * Metodo que permite buscar productos
     * @param nombre, model
     * @return producto.html
     */
    @GetMapping("buscar-producto")
    public ModelAndView buscarByNombreCategoria(@RequestParam("nombre") String buscado,@RequestParam("categoria") Long id, Model model){
        ModelAndView modelView = new ModelAndView("producto");
        List<Producto> coincidenteList = new ArrayList<Producto>();
        List<Producto> categoriaList = new ArrayList<Producto>();
        if(id!=0){
            categoriaList=productoService.findByCategoria(categoriaService.findCategoriaById(id));
        }else{
            categoriaList=productoService.getDisponibles();
        }
        for(Producto producto:categoriaList){
            if(producto.getNombre().toLowerCase().contains(buscado.toLowerCase())){
                coincidenteList.add(producto);
            }
        }
        modelView.addObject("listaCategorias", categoriaService.getDisponibles());
        modelView.addObject("listaProductos", coincidenteList);
        if(coincidenteList.size()==0){
            modelView.addObject("alerta",true);
        }
        return modelView;
    }
    /**
     * Este metodo es el metodo de carga de las imagenes
     * @param filename
     * @return ResponseEntity
     */
    @GetMapping("uploads/{filename}")
    public ResponseEntity<Resource> cargarImagen(@PathVariable String filename){
        Resource resource = null;
        try{
            resource = uploadFile.load(filename);
        }catch (MalformedURLException e ){
            e.printStackTrace();
        }return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
    
}
   
