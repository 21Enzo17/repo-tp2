package ar.edu.unju.fi.service.imp;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.fi.Listas.ListaProducto;
import ar.edu.unju.fi.model.Producto;
import ar.edu.unju.fi.service.IProductoService;
import ar.edu.unju.fi.util.UploadFile;

import jakarta.validation.Valid;

@Service
public class ProductoServiceImp implements IProductoService {
    @Autowired
    private ListaProducto listaProductos;

    @Autowired
    private Producto formProducto;

    @Autowired
    private UploadFile uploadFile;
     /**
     * Metodo que muestra la pagina de productos
     * @param model
     * @return producto.html

     */
    @Override
    public ModelAndView getProductos(Model model ) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("producto");
        modelView.addObject("buscado");
        modelView.addObject("listaProductos", listaProductos.getProductos());
        return modelView;
    }

    /**
     * Metodo que muestra la pagina de agregar producto
     * @param model
     * @return nuevo-producto.html
     */
    @Override
    public ModelAndView getNuevoProductoPage(Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("nuevo-producto");
        modelView.addObject("formProducto", formProducto);
        return modelView;
    }

    /**
     * Metodo que crea un producto y lo agrega a la lista
     * @param formProducto
     * @return  nuevo-producto.html
     */
    @Override
    public ModelAndView crearProducto(@Valid @ModelAttribute("formProducto")Producto formProducto,BindingResult result, @RequestParam("file") MultipartFile image) throws Exception {
        // TODO Auto-generated method stub
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("nuevo-producto");
        }else{
            modelView = new ModelAndView("producto");
            String uniqueFileName = uploadFile.copy(image);
            formProducto.setImagen(uniqueFileName);
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
    @Override
    public ModelAndView eliminarProducto(int codigo, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("producto");
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                uploadFile.delete(producto.getImagen());
                listaProductos.getProductos().remove(producto);
                break;
            }
        }
        modelView.addObject("listaProductos", listaProductos.getProductos());
        return modelView;
    }

    /**
     * Metodo que permite editar un producto
     * @param codigo
     * @param model
     * @return modificar-producto.html
     */
    @Override
    public ModelAndView editarProducto(int codigo, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("modificar-producto");
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                modelView.addObject("encontrado", producto);
                break;
            }
        }
        return modelView;
    }

    /**
     * Metodo que modifica el producto
     * @param modificado
     * @return producto.html
     */
    @Override
    public ModelAndView modificarLista(@Valid Producto modificado, BindingResult result, @RequestParam("file") MultipartFile image) throws Exception {
        // TODO Auto-generated method stub
        ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-producto");
        }else{
            for(Producto producto:listaProductos.getProductos()){
                if(modificado.getCod()==producto.getCod()){
                    producto.setNombre(modificado.getNombre());
                    producto.setCategoria(modificado.getCategoria());
                    producto.setDescuento(modificado.getDescuento());
                    uploadFile.delete(producto.getImagen());
                    String uniqueFileName = uploadFile.copy(image);
                    producto.setImagen(uniqueFileName);
                    producto.setPrecio(modificado.getPrecio());
                    break;
                }
            }
            modelView = new ModelAndView("producto");
            modelView.addObject("listaProductos", listaProductos.getProductos());
        }
        return modelView;
    }
    /**
     * Metodo que permite buscar productos
     * @param nombre, model
     * @return producto.html
     */
    @Override
    public ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado, Model model){
        ModelAndView modelView = new ModelAndView("producto");
        
        List<Producto> coincidenteList = new ArrayList<Producto>();
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getNombre().toLowerCase().contains(buscado.toLowerCase())){
                coincidenteList.add(producto);
            }
        }
        modelView.addObject("listaProductos", coincidenteList);
        if(coincidenteList.size()==0){
            modelView.addObject("alerta",true);
        }
        return modelView;
    }

    @Override
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
