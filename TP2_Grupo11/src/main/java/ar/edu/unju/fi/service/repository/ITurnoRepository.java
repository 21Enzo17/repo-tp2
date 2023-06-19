package ar.edu.unju.fi.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Turno;

@Repository
public interface ITurnoRepository extends CrudRepository<Turno,Long>{
	
	public List<Turno> findByEstado(boolean estado);
}
