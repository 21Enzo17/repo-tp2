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
        productos.add(new Producto("Maintance Criadores", 1, 4500, "Alimento", 10, "https://www.baires-sa.com.ar/img_productos/11051359_BairesMaintenance-CriadoresPack01.png"));
        productos.add(new Producto("Alimento Pro Plan Adulto", 2, 9005, "Alimento", 10, "https://cdn.shopify.com/s/files/1/0550/6252/8170/products/PurinaProPlanGatoAdulto7_45f40ee7-f964-4e9c-8e4f-3c04d9fda5f5_1024x.png?v=1672154260"));        
        productos.add(new Producto("Hueso Nylon", 3, 3500, "Juguetes", 5, "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/145/238/products/963-730x7001-8592d33764e843dc5215870169313797-640-0.webp"));
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
