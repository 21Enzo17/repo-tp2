package ar.edu.unju.fi.repository;

import ar.edu.unju.fi.entity.Contacto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends CrudRepository<Contacto, Long> {
}
