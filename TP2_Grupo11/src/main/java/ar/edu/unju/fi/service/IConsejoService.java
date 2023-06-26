
package ar.edu.unju.fi.service;

import ar.edu.unju.fi.entity.Autor;
import ar.edu.unju.fi.entity.Consejo;
import java.util.List;

public interface IConsejoService {
	
	
    public List<Consejo> getConsejos(); 
    
    public List<Consejo> getDisponibles();

    public void addConsejo(Consejo consejo);

    public void eliminarConsejo(Consejo consejo);

    public Consejo findConsejoById(Long id);
    
    public List<Consejo> findByAutor(Autor autor);

}


