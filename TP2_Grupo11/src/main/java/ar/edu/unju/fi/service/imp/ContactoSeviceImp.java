package ar.edu.unju.fi.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Contacto;
import ar.edu.unju.fi.repository.ContactoRepository;
import ar.edu.unju.fi.service.IContactoService;
@Service

public class ContactoSeviceImp implements IContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	@Override
	public void crearContacto(Contacto contacto) {
		// TODO Auto-generated method stub
			contacto.setEstado(true);
			contactoRepository.save(contacto);
	}
	
}
