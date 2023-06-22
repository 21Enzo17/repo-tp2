package ar.edu.unju.fi.service;

import ar.edu.unju.fi.entity.Provincia;
import java.util.List;

public interface IProvinciaService {
    public void guardarProvincia(Provincia formProvincia);
    public void eliminarProvincia(Long id);
    public void modificarProvincia(Provincia provinciaEditado);
    public List<Provincia> obtenerProvincias();
    public Provincia getProvinciaById(Long id);
    public Provincia getProvincia();


}
