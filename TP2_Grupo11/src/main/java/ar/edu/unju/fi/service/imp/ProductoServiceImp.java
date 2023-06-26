package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.entity.Producto;
import ar.edu.unju.fi.repository.IProductoRepository;


import ar.edu.unju.fi.service.IProductoService;


@Service("productoServiceImp")
public class ProductoServiceImp implements IProductoService {
    
    @Autowired
    private IProductoRepository productoRepository;


    @Override
    public List<Producto> getAllProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public List<Producto> getDisponibles() {
        return productoRepository.productosDisponibles();
    }

    @Override
    public void addProducto(Producto producto) {
        producto.setEstado(true);
        productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        producto.setEstado(false);
        productoRepository.save(producto);
    }

    @Override
    public Producto findProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria){
        return productoRepository.buscaPorCategoria(categoria);
    }
}
