package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Autor;
import ar.edu.unju.fi.repository.IAutorRepository;
import ar.edu.unju.fi.service.IAutorService;

@Service
public class AutorServiceImp implements IAutorService {

    @Autowired
    private IAutorRepository autorRepository;
	
    @Override
	public List<Autor> getAllAutores() {
		return (List<Autor>) autorRepository.findAll();
	}

	@Override
	public List<Autor> getDisponibles() {
		return autorRepository.autoresDisponibles();
	}

	@Override
	public void addAutor(Autor autor) {
		autorRepository.save(autor);
		
	}

	@Override
	public void eliminarAutor(Autor autor) {
        autor.setEstado(false);
        autorRepository.save(autor);
		
	}

	@Override
	public Autor findAutorById(Long id) {
		return autorRepository.findById(id).orElse(null);
	}


	
}
