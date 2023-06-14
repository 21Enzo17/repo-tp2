package ar.edu.unju.fi.Listas;

import ar.edu.unju.fi.entity.Sucursal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListaSucursal {
    private List<Sucursal> listaSucursales;

    public ListaSucursal() {
        this.listaSucursales = new ArrayList<Sucursal>();
        // Elementos precargados en la lista
        /*listaSucursales.add(new Sucursal("Las Vicuñas 890, San Salvador de Jujuy, Jujuy", "+54 9 11 5587 5274", "vicuñas@vet.com.ar", "Lunes a viernes: 8 a 21 hs"));
        listaSucursales.add(new Sucursal("Av. España 1650, San Salvador de Jujuy, Jujuy", "+54 11 2259 4709", "avespaña@vet.com.ar", "Lunes a viernes: 8 a 21 hs"));
        listaSucursales.add(new Sucursal("Juana Manuela Gorriti 315, San Salvador de Jujuy, Jujuy", "+54 9 11 6696 2038", "gorriti@vet.com.ar", "Lunes a viernes: 8 a 21 hs"));*/
    }

    public ListaSucursal(List<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    /**
     * Método que permite agregar una sucursal a la lista de sucursales
     * @param sucursal
     */
    public void agregarSucursal(Sucursal sucursal){
        listaSucursales.add(sucursal);
    }

    public List<Sucursal> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    @Override
    public String toString() {
        return "ListaSucursal{" +
                "listaSucursales=" + listaSucursales +
                '}';
    }
}
