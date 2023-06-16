package ar.edu.unju.fi.repository;

import ar.edu.unju.fi.entity.Provincia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {
    @Query(value="SELECT p FROM Provincia p WHERE p.estado=true")
    public List<Provincia> provinciasDisponibles();
}
