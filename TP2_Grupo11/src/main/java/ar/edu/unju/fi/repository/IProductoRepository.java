package ar.edu.unju.fi.repository;


import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends CrudRepository<Producto,Long> {
    
    @Query(value="SELECT s FROM Producto s WHERE s.estado=true")
    public List<Producto> productosDisponibles();

    @Query("select s from Producto s WHERE s.categoria=?1 and s.estado=true")
    public List<Producto> buscaPorCategoria(Categoria categoria);

}
