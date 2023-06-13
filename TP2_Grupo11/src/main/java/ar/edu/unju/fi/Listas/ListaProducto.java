 package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Producto;
// quite java util objects
@Component
public class ListaProducto {
    private List<Producto> productos;

    public ListaProducto(){
        productos = new ArrayList<Producto>();
        //Productos precargados
        productos.add(new Producto("Maintance Criadores", 1, 4500, "Alimento", 10, "MaintancePre.png"));
        productos.add(new Producto("Alimento Pro Plan Adulto", 2, 9005, "Alimento", 10, "PurinaProPlanGatoAdulto7.webp"));        
        productos.add(new Producto("Hueso Nylon", 3, 3500, "Juguetes", 5, "JuguetePerroNylon.webp"));
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
