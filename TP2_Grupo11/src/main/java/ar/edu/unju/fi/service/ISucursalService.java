package ar.edu.unju.fi.service;


import ar.edu.unju.fi.entity.Sucursal;
import java.time.LocalTime;
import java.util.List;

public interface ISucursalService {
    public List<Sucursal> getSucursales();
    public void guardarSucursal(Sucursal formSucursal);
    public void eliminarSucursal(Long id);
    public void modificarSucursal(Sucursal sucursalEditado);
    public List<Sucursal> getSucursalByDireccion(String query);
    public Sucursal getSucursalById(Long id);
    public List<Sucursal> filtrarSucursal(LocalTime horaInicio, LocalTime horaFin);
    Sucursal getSucursal();
}
