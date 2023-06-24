 package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Producto;
// quite java util objects
@Component
public class ListaProducto {
    private List<Producto> productos;

    public ListaProducto(){
        productos = new ArrayList<Producto>();
    }
    public void addProductos(Producto nuevoProducto){
        this.productos.add(nuevoProducto);
    }

    public ListaProducto(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ListaProducto productos(List<Producto> productos) {
        setProductos(productos);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " productos='" + getProductos() + "'" +
            "}";
    }

    
}
