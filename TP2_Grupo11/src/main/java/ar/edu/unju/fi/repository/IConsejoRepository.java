package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Autor;
import ar.edu.unju.fi.entity.Consejo;

@Repository
public interface IConsejoRepository extends CrudRepository<Consejo, Long>{

	@Query(value="SELECT c FROM Consejo c WHERE c.estado=true")
	public List<Consejo> consejosDisponibles();
	
    @Query("select c from Consejo c WHERE c.autor=?1 and c.estado=true")
    public List<Consejo> buscarPorAutor(Autor autor);
}
