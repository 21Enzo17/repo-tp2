package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.entity.Sucursal;
import ar.edu.unju.fi.repository.SucursalRepository;
import ar.edu.unju.fi.service.IProvinciaService;
import ar.edu.unju.fi.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("sucursalServiceMysqlImp")
public class SucursalServiceMysqlImp implements ISucursalService {
    @Autowired
    private Sucursal sucursal;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    IProvinciaService provinciaService;

    /**
     * Método que devuelve la lista de sucursales disponibles (con estado en true)
     * @return las sucursales con estado en true
     */
    @Override
    public List<Sucursal> getSucursales() {
        return sucursalRepository.sucursalesDisponibles();
    }

    /**
     * Método que guarda una sucursal en la lista
     * @param formSucursal
     */
    @Override
    public void guardarSucursal(Sucursal formSucursal) {
        sucursalRepository.save(formSucursal);
    }

    /**
     * Método que elimina una sucursal de la lista
     * @param id
     */
    @Override
    public void eliminarSucursal(Long id) {
        Sucursal sucursal1 = sucursalRepository.findById(id).orElse(null);
        sucursal1.setEstado(false);
        sucursalRepository.save(sucursal1);
    }

    /**
     * Método que modifica una sucursal de la lista
     * @param sucursalEditado
     */
    @Override
    public void modificarSucursal(Sucursal sucursalEditado) {
        sucursalRepository.save(sucursalEditado);
    }

    /**
     * Método que devuelve una lista de sucursales que coinciden con la dirección ingresada
     * @param query
     * @return las sucursales que coinciden con la dirección ingresada
     */
    @Override
    public List<Sucursal> getSucursalByDireccion(String query) {
        List<Sucursal> queryResult = new ArrayList<>();
        for (Sucursal sucursal : sucursalRepository.sucursalesDisponibles()){
            if(sucursal.getDireccion().toLowerCase().contains(query.toLowerCase())){
                queryResult.add(sucursal);
            }
        }
        return queryResult;
    }

    /**
     * Método que devuelve una sucursal que coincide con el id ingresado
     * @param id
     * @return la sucursal que coincide con el id ingresado
     */
    @Override
    public Sucursal getSucursalById(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    /**
     * Método que devuelve una lista de sucursales que coinciden con el horario ingresado
     * @param horaInicio
     * @param horaFin
     * @return las sucursales que coinciden con el horario ingresado
     */
    @Override
    public List<Sucursal> filtrarSucursal(LocalTime horaInicio, LocalTime horaFin) {
        List<Sucursal> queryResult = new ArrayList<>();
        for (Sucursal sucursal : sucursalRepository.sucursalesDisponibles()){
            if(sucursal.getHorarioInicio().isAfter(horaInicio) && sucursal.getHorarioFin().isBefore(horaFin)){
                queryResult.add(sucursal);
            }
        }
        return queryResult;
    }

    /**
     * Método que devuelve una sucursal del model
     * @return sucursal del model
     */
    @Override
    public Sucursal getSucursal() {
        return sucursal;
    }
}
