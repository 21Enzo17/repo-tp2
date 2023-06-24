package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.entity.Provincia;
import ar.edu.unju.fi.repository.ProvinciaRepository;
import ar.edu.unju.fi.service.IProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceMysqlImp implements IProvinciaService {
    @Autowired
    Provincia provincia;
    @Autowired
    ProvinciaRepository provinciaRepository;

    /**
     * Método que guarda una provincia en la base de datos.
     * @param formProvincia
     */
    @Override
    public void guardarProvincia(Provincia formProvincia) {
        provinciaRepository.save(formProvincia);
    }

    /**
     * Método que elimina lógicamente (colocando su estado en false) una provincia de la base de datos.
     * @param id
     */
    @Override
    public void eliminarProvincia(Long id) {
        Provincia prov = provinciaRepository.findById(id).get();
        prov.setEstado(false);
        provinciaRepository.save(prov);
    }

    /**
     * Método que modifica una provincia de la base de datos.
     * @param provinciaEditado
     */
    @Override
    public void modificarProvincia(Provincia provinciaEditado) {
        provinciaRepository.save(provinciaEditado);
    }

    /**
     * Método que devuelve una lista de provincias disponibles (con estado en true).
     * @return las provincias con estado en true.
     */
    @Override
    public List<Provincia> obtenerProvincias(){
        return provinciaRepository.provinciasDisponibles();
    }

    /**
     * Método que devuelve una provincia de la base de datos.
     * @param id
     * @return la provincia con el id pasado por parámetro.
     */
    @Override
    public Provincia getProvinciaById(Long id) {
        return provinciaRepository.findById(id).get();
    }

    /**
     * Método que devuelve una provincia.
     * @return la provincia del model.
     */
    @Override
    public Provincia getProvincia() {
        return provincia;
    }

}
