package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.Listas.ListaSucursal;
import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.service.ISucursalService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalServiceImp implements ISucursalService {
    @Autowired
    private ListaSucursal listaSucursales;
    @Autowired
    private Sucursal sucursal;

    /**
     * Método que devuelve la lista de sucursales
     * @return listaSucursales
     */
    @Override
    public List<Sucursal> getSucursales() {
        return listaSucursales.getListaSucursales();
    }

    /**
     * Método que guarda una sucursal en la lista
     * @param sucursal
     */
    @Override
    public void guardarSucursal(Sucursal sucursal) {
        listaSucursales.getListaSucursales().add(sucursal);
    }

    /**
     * Método que devuelve una lista de sucursales que coinciden con la búsqueda
     * @param query
     * @return queryResult
     */
    @Override
    public List<Sucursal> getSucursalByDireccion(String query) {
        List<Sucursal> queryResult = new ArrayList<>();
        for (Sucursal sucursal : listaSucursales.getListaSucursales()){
            if(sucursal.getDireccion().toLowerCase().contains(query.toLowerCase())){
                queryResult.add(sucursal);

            }
        }
        return queryResult;
    }

    @Override
    public Sucursal getSucursalById(Long id) {
        return null;
    }

    @Override
    public List<Sucursal> filtrarSucursal(LocalTime horaInicio, LocalTime horaFin) {
        return null;
    }

    /**
     * Método que modifica una sucursal de la lista
     * @param sucursalEditado
     */
    @Override
    public void modificarSucursal(Sucursal sucursalEditado){
        for(Sucursal sucursal:getSucursales()){
            if(sucursal.getDireccion().equals(sucursalEditado.getDireccion())){
                sucursal.setDireccion(sucursalEditado.getDireccion());
                sucursal.setTelefono(sucursalEditado.getTelefono());
                sucursal.setHorarioInicio(sucursalEditado.getHorarioInicio());
                sucursal.setHorarioFin(sucursalEditado.getHorarioFin());
                sucursal.setMail(sucursalEditado.getMail());
                break;
            }
        }
    }

    /**
     * Método que elimina una sucursal de la lista
     * @param id
     */
    @Override
    public void eliminarSucursal(Long id) {
        for(Sucursal sucursal:listaSucursales.getListaSucursales()){
            if(sucursal.getId()==id){
                listaSucursales.getListaSucursales().remove(sucursal);
                break;
            }
        }
    }

    /**
     * Método que devuelve una sucursal
     * @return sucursal
     */
    @Override
    public Sucursal getSucursal() {
        return sucursal;
    }
}
