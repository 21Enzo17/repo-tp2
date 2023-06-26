package ar.edu.unju.fi.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Empleado;
@Repository
public interface IEmpleadoRepository extends CrudRepository<Empleado,Long> {
	
	public List<Empleado> findByEstado(Boolean estado);
	public Empleado findByCod(Long cod);

}
