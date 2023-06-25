package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Categoria;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria,Long>{
    @Query(value="SELECT s FROM Categoria s WHERE s.estado=true")
    public List<Categoria> categoriasDisponibles();
}

