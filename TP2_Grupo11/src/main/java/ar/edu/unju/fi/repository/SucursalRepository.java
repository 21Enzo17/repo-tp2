package ar.edu.unju.fi.repository;

import ar.edu.unju.fi.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends CrudRepository<Sucursal, Long> {
    @Query(value="SELECT s FROM Sucursal s WHERE s.estado=true")
    public List<Sucursal> sucursalesDisponibles();
}
