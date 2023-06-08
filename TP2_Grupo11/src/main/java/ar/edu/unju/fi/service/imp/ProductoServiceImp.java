package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaProducto;
import ar.edu.unju.fi.model.Producto;
import ar.edu.unju.fi.service.IProductoService;

import jakarta.validation.Valid;

@Service
public class ProductoServiceImp implements IProductoService {
    @Autowired
    private ListaProducto listaProductos;

    @Autowired
    private Producto formProducto;

    @Override
    public ModelAndView getProductos(Model model ) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("producto");
        modelView.addObject("buscado");
        modelView.addObject("listaProductos", listaProductos.getProductos());
        return modelView;
    }

    @Override
    public ModelAndView getNuevoProductoPage(Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("nuevo-producto");
        modelView.addObject("formProducto", formProducto);
        return modelView;
    }

    @Override
    public ModelAndView crearProducto(@Valid Producto formProducto, BindingResult result) {
        // TODO Auto-generated method stub
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

    @Override
    public ModelAndView eliminarProducto(int codigo, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("producto");
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                listaProductos.getProductos().remove(producto);
                break;
            }
        }
        modelView.addObject("listaProductos", listaProductos.getProductos());
        return modelView;
    }

    @Override
    public ModelAndView editarProducto(int codigo, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("modificar-producto");
        for(Producto producto:listaProductos.getProductos()){
            if(producto.getCod()==codigo){
                modelView.addObject("encontrado", producto);
                System.out.println(producto.toString());
                break;
            }
        }
        return modelView;
    }

    @Override
    public ModelAndView modificarLista(@Valid Producto modificado, BindingResult result) {
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
}
