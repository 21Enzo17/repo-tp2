package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaSemana;
import ar.edu.unju.fi.entity.Dia;
import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.entity.Turno;
import ar.edu.unju.fi.service.IServicioService;
import ar.edu.unju.fi.service.repository.IEmpleadoRepository;
import ar.edu.unju.fi.service.repository.ITurnoRepository;
@Service("ServicioServiceMySql")
public class ServicioServiceMySqlImp implements IServicioService {
	
	@Autowired
	ITurnoRepository turnoRepository;
	@Autowired
	IEmpleadoRepository empleadoRepository;	
	@Autowired
	Turno turno;
	@Autowired
	Empleado empleado;
	
	@Autowired
	ListaSemana semana;

	@Override
	public List<Turno> getHorarios() {
		// TODO Auto-generated method stub
		return turnoRepository.findByEstado(true);
	}

	@Override
	public boolean existe(String dia) {
		// TODO Auto-generated method stub
		boolean band=false;
		List<Turno> disponibles=turnoRepository.findByEstado(true);
		for(Turno turno: disponibles) {
			if(turno.getDia().equals(dia)) {
				band=true;
			}
		}
		return band; 
	}

	@Override
	public List<Dia> getSemana() {
		// TODO Auto-generated method stub
		return semana.getSemana();
	}

	@Override
	public List<Turno> getListaOrdenada() {
		// TODO Auto-generated method stub
		List<Turno> disponibles=turnoRepository.findByEstado(true);
		List<Turno> listaOrdenada=new ArrayList<>();
		for(int i=1;i<=6;i++) {
    		for(Turno turno: disponibles) {
    			if(turno.getCod()==i) {
    				listaOrdenada.add(turno);
    			}
    		}
		}
		return listaOrdenada;
	}

	@Override
	public Turno getTurno() {
		// TODO Auto-generated method stub
		return turno;
	}

	@Override
	public List<String> getDisponibles() {
		// TODO Auto-generated method stub
		List<Turno> disponibles=turnoRepository.findByEstado(true);
		List<String> diasDisponibles = new ArrayList<>();
		
		for(Dia ddia: semana.getSemana()) {
			boolean band=true;
			for(Turno turno : disponibles) {
				if(ddia.getNombre().equals(turno.getDia())) {band=false;}
			}
			if(band) {
				diasDisponibles.add(ddia.getNombre());
			}
		}	
		return diasDisponibles;
	}

	@Override
	public boolean semanaCompleta() {
		// TODO Auto-generated method stub
		List<Turno> disponibles=turnoRepository.findByEstado(true);

		return disponibles.size()!=6;
	}

	@Override
	public List<Turno> guardarTurno(Turno formHorario) {
		// TODO Auto-generated method stub
		formHorario.autoAsignarId(formHorario);
		turnoRepository.save(formHorario);
		return turnoRepository.findByEstado(true);
	}

	@Override
	public Turno getTurno(String dia) {
		// TODO Auto-generated method stub
		List<Turno> disponibles=turnoRepository.findByEstado(true);
		Turno turnoDia = new Turno();

        for(Turno lhora: disponibles) {
        	if(lhora.getDia().equals(dia)){
        		turnoDia = lhora;
        		System.out.println(turnoDia.toString());
        		break;
        	}
        }

		return turnoDia;
	}

	@Override
	public void eliminarHorario(String dia) {
		// TODO Auto-generated method stub
		List<Turno> disponibles=turnoRepository.findByEstado(true);
		Turno turnoDia = new Turno();

		for(Turno lhora: disponibles) {
        	if(lhora.getDia().equals(dia)){
        		turnoDia = lhora;
        		turnoDia.setEstado(false);
        		turnoRepository.save(turnoDia);
        		break;
        	}
        }
	}

	@Override
	public List<Turno> guardarCambios(Turno modificado) {
		// TODO Auto-generated method stub
		modificado.autoAsignarId(modificado);
		turnoRepository.save(modificado);
		return turnoRepository.findByEstado(true);
	}

	@Override
	public List<Turno> buscarPorNombre(String buscado) {
		// TODO Auto-generated method stub
		List<Turno> disponibles = turnoRepository.findByEstado(true);
		List<Turno> coincidenteList = new ArrayList<Turno>();
		for (Turno turno : disponibles) {
			if (turno.getDia().toLowerCase().contains(buscado.toLowerCase())
					|| turno.getPaseador1().toLowerCase().contains(buscado.toLowerCase())
					|| turno.getPaseador2().toLowerCase().contains(buscado.toLowerCase())) {
				coincidenteList.add(turno);
			}
		}

		return coincidenteList;
	}

	@Override
	public void guardar(Turno turno) {
		// TODO Auto-generated method stub
		turno.setEstado(true);
		turnoRepository.save(turno);
		
	}

	@Override
	public void modificar(Turno turno) {
		// TODO Auto-generated method stub
		turnoRepository.save(turno);
	}

	@Override
	public void eliminar(Turno turno) {
		// TODO Auto-generated method stub
		turno.setEstado(false);
		turnoRepository.save(turno);
		
	}

	@Override
	public Empleado getEmpleado() {
		// TODO Auto-generated method stub
		return empleado;
	}

	@Override
	public void guardar(Empleado empleado) {
		// TODO Auto-generated method stub
		empleado.setEstado(true);
		empleadoRepository.save(empleado);
	}

	@Override
	public void modificar(Empleado empleado) {
		// TODO Auto-generated method stub
		empleadoRepository.save(empleado);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		List<Empleado> encargados=empleadoRepository.findByEstado(true);
		Empleado emple=new Empleado();
		for(Empleado emp: encargados) {
        	if(emp.getCod()==id){
        		emple=emp;
        		break;
        	}
        }
		emple.setEstado(false);
		empleadoRepository.save(emple);
	}

	@Override
	public List<Empleado> getlistEmpleados() {
		// TODO Auto-generated method stub
		List<Empleado> listempleados = empleadoRepository.findByEstado(true);

		return listempleados;
	}

	@Override
	public void eliminar(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Empleado getEmpleado(Long cod) {
		// TODO Auto-generated method stub		
		return empleadoRepository.findByCod(cod);
	}

}
