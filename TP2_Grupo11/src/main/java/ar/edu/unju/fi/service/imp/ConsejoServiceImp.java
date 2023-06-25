package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaConsejo;
import ar.edu.unju.fi.entity.Autor;
import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.repository.IConsejoRepository;
import ar.edu.unju.fi.service.IConsejoService;
import jakarta.validation.Valid;

@Service
public class ConsejoServiceImp implements IConsejoService {
	
	@Autowired
    private IConsejoRepository consejoRepository;
	
	@Override
	public List<Consejo> getConsejos() {
		return (List<Consejo>) consejoRepository.findAll();
	}

	@Override
	public List<Consejo> getDisponibles() {
		return consejoRepository.consejosDisponibles();
	}

	@Override
	public void addConsejo(Consejo consejo) {
		consejo.setEstado(true);
		consejoRepository.save(consejo);
	}

	@Override
	public void eliminarConsejo(Consejo consejo) {
		consejo.setEstado(false);
		consejoRepository.save(consejo);
	}

	@Override
	public Consejo findConsejoById(Long id) {
		return consejoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Consejo> findByAutor(Autor autor) {
		return consejoRepository.buscarPorAutor(autor);
	}


}
