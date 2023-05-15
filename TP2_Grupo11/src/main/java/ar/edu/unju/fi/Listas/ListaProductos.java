package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.model.Producto;
// quite java util objects
public class ListaProductos {
    private List<Producto> productos;

    public ListaProductos(){
        productos = new ArrayList<Producto>();
    }
    public void addProductos(Producto nuevoProducto){
        this.productos.add(nuevoProducto);
    }

    public ListaProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ListaProductos productos(List<Producto> productos) {
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
