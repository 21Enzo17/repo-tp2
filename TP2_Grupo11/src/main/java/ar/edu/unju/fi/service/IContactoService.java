package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Contacto;

public interface IContactoService {
	
	void crearContacto(Contacto contacto);

	void eliminarContacto(Long id);
	
	public List<Contacto> getListaDeContactos();
	
	
}

