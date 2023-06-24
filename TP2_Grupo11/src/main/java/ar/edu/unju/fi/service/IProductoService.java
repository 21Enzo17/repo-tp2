package ar.edu.unju.fi.service;



import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.entity.Producto;
import java.util.List;


public interface IProductoService{
    
    public List<Producto> getAllProductos(); // Listar

    public List<Producto> getDisponibles(); // Listar Disponibles

    public void addProducto(Producto producto); // Modificar y Guardar

    public void eliminarProducto(Producto producto);

    public Producto findProductoById(Long id); // Buscar por ID

    public List<Producto> findByCategoria(Categoria categoria);
    
}
