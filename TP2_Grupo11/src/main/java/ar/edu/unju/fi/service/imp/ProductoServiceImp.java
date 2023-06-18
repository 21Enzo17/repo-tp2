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
import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.entity.Producto;
import ar.edu.unju.fi.repository.IProductoRepository;
import ar.edu.unju.fi.service.ICategoriaService;
import ar.edu.unju.fi.service.IProductoService;
import ar.edu.unju.fi.util.UploadFile;

import jakarta.validation.Valid;

@Service("productoServiceImp")
public class ProductoServiceImp implements IProductoService {
    @Autowired
    private ListaProducto listaProductos;
    
    @Autowired
    private Producto formProducto;

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ICategoriaService categoriaService;

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
        modelView.addObject("listaProductos", productoRepository.productosDisponibles());
        modelView.addObject("listaCategorias", categoriaService.getDisponibles()); 
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
        modelView.addObject("listaCategorias", categoriaService.getDisponibles());
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
            formProducto.setEstado(true);
            productoRepository.save(formProducto);
            modelView.addObject("listaProductos", productoRepository.productosDisponibles());
        }
        return modelView;
    }

    /**
     * Metodo que elimina un producto de la lista
     * @param id
     * @param model
     * @return producto.html
     */
    @Override
    public ModelAndView eliminarProducto(Long id, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("producto");
        for(Producto producto:productoRepository.findAll()){
            if(producto.getId()==id){
                System.out.println("#### Encontrado ####" + producto.getId() + "###" + id);
                producto.setEstado(false);
                productoRepository.save(producto);
                System.out.println("#### Guardado ####");
                break;
            }
        }
        modelView.addObject("listaProductos", productoRepository.productosDisponibles());
        return modelView;
    }

    /**
     * Metodo que permite editar un producto
     * @param id
     * @param model
     * @return modificar-producto.html
     */
    @Override
    public ModelAndView editarProducto(Long id, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("modificar-producto");
        modelView.addObject("listaCategorias", categoriaService.getDisponibles());
        for(Producto producto:productoRepository.findAll()){
            if(producto.getId()==id){
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
            for(Producto producto:productoRepository.findAll()){
                if(modificado.getId()==producto.getId()){
                    producto.setNombre(modificado.getNombre());
                    producto.setCategoria(modificado.getCategoria());
                    producto.setDescuento(modificado.getDescuento());
                    if(!image.isEmpty()){
                        uploadFile.delete(producto.getImagen());
                        String uniqueFileName = uploadFile.copy(image);
                        producto.setImagen(uniqueFileName);
                    }
                    producto.setPrecio(modificado.getPrecio());
                    productoRepository.save(producto);
                    break;
                }
            }
            modelView = new ModelAndView("producto");
            modelView.addObject("listaProductos", productoRepository.productosDisponibles());
        }
        return modelView;
    }
    /**
     * Metodo que permite buscar productos
     * @param nombre, model
     * @return producto.html
     */
    @Override
    public ModelAndView buscar(@RequestParam("nombre") String buscado,@RequestParam("categoria") Long id, Model model){
        ModelAndView modelView = new ModelAndView("producto");
        List<Producto> coincidenteList = new ArrayList<Producto>();
        if(id!=0){
           for(Producto producto:productoRepository.buscaPorCategoria(categoriaService.findCategoriaById(id))){
            if(producto.getNombre().toLowerCase().contains(buscado.toLowerCase())){
                coincidenteList.add(producto);
            }
        }
        }else{
            for(Producto producto:productoRepository.productosDisponibles()){
            if(producto.getNombre().toLowerCase().contains(buscado.toLowerCase())){
                coincidenteList.add(producto);
            }
        }
        }
        modelView.addObject("listaCategorias", categoriaService.getDisponibles());
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
