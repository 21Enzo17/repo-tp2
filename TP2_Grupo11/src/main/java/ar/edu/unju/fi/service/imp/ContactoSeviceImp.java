package ar.edu.unju.fi.service.imp;

import java.util.List;

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

	@Override
	public void eliminarContacto(Long id) {
		// TODO Auto-generated method stub
		List<Contacto> contactos= contactoRepository.findByEstado(true);
		Contacto contacto = new Contacto();
		for(Contacto emp: contactos) {
        	if(emp.getId()==id){
        		contacto= emp;
        		break;
        	}
        }
		contacto.setEstado(false);
		contactoRepository.save(contacto);
		
	}

	@Override
	public List<Contacto> getListaDeContactos() {
		// TODO Auto-generated method stub
		List <Contacto> lista = contactoRepository.findByEstado(true); 
		return lista;
	}

	
}
