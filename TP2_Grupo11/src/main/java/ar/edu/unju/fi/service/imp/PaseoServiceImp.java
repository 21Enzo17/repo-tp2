package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaHorario;
import ar.edu.unju.fi.Listas.ListaSemana;
import ar.edu.unju.fi.entity.Dia;

import ar.edu.unju.fi.entity.Empleado;
import ar.edu.unju.fi.entity.Turno;
import ar.edu.unju.fi.service.IServicioService;

/**
 * @author JohanR. Implementación de Servicios usando Datos generados en las
 *         Clases.
 */
@Service("paseoService")
public class PaseoServiceImp implements IServicioService {

	@Autowired
	ListaHorario listaDeHorarios;
	@Autowired
	ListaSemana semana;
	@Autowired
	Turno turno;

	/**
	 * Método que retorna Una lista de Horarios.
	 */
	@Override
	public List<Turno> getHorarios() {
		// TODO Auto-generated method stub
		return listaDeHorarios.getHorarios();
	}

	/**
	 * Método que verifica si dia esta en la lista de Turnos disponibles.
	 */
	@Override
	public boolean existe(String dia) {
		// TODO Auto-generated method stub
		return listaDeHorarios.existe(dia);
	}

	/**
	 * Métpdo retorna lista con los dias de la Semana(Lunes a Sabado).
	 */
	@Override
	public List<Dia> getSemana() {
		// TODO Auto-generated method stub
		return semana.getSemana();
	}

	/**
	 * Método que Ordena una lista de turnos
	 */
	@Override
	public List<Turno> getListaOrdenada() {
		// TODO Auto-generated method stub
		List<Turno> listaOrdenada = new ArrayList<Turno>();
		for (int i = 1; i <= 6; i++) {
			for (Turno turno : listaDeHorarios.getHorarios()) {
				if (turno.getCod() == i) {
					listaOrdenada.add(turno);
				}
			}
		}
		return listaOrdenada;
	}

	/**
	 * Método retorna un Objeto de tipo Turno.
	 */
	@Override
	public Turno getTurno() {
		// TODO Auto-generated method stub
		return turno;
	}

	/**
	 * Método que verifica que dias no estan dentro de una lista de Horarios y
	 * retorna una Lista con ellos.
	 */
	@Override
	public List<String> getDisponibles() {
		// TODO Auto-generated method stub
		List<String> disponibles = new ArrayList<>();

		for (Dia dia : semana.getSemana()) {
			if (listaDeHorarios.existe(dia.getNombre()) == false) {
				disponibles.add(dia.getNombre());
			}
		}
		return disponibles;
	}

	/**
	 * Método que verifica si una Lista de Horarios está Completa.
	 */
	@Override
	public boolean semanaCompleta() {
		// TODO Auto-generated method stub
		return (listaDeHorarios.getHorarios().size() < 6);
	}

	/**
	 * metodo que guarda un Objeto Turno en la lista de Horariod Disponibles y
	 * retorna la lista ordenada.
	 */
	@Override
	public List<Turno> guardarTurno(Turno formHorario) {
		// TODO Auto-generated method stub

		formHorario.autoAsignarId(formHorario);
		listaDeHorarios.getHorarios().add(formHorario);
		List<Turno> listaOrdenada = new ArrayList<Turno>();
		for (int i = 1; i <= 6; i++) {
			for (Turno turno : listaDeHorarios.getHorarios()) {
				if (turno.getCod() == i) {
					listaOrdenada.add(turno);
				}
			}
		}
		return listaOrdenada;
	}

	/**
	 * Método que imprime en un Formulario los valores de Objeto tipo Turno
	 * identificado por dia.
	 */
	@Override
	public Turno getTurno(String dia) {
		// TODO Auto-generated method stub
		Turno turnoDia = new Turno();

		for (Turno lhora : listaDeHorarios.getHorarios()) {
			if (lhora.getDia().equals(dia)) {
				turnoDia = lhora;
				System.out.println(turnoDia.toString());
				break;
			}
		}
		return turnoDia;
	}

	/**
	 * Elimina un Objeto de tipo turno identificado como dia de una lista de
	 * Horarios.
	 */
	@Override
	public void eliminarHorario(String dia) {
		// TODO Auto-generated method stub
		for (Turno lhora : listaDeHorarios.getHorarios()) {
			if (lhora.getDia().equals(dia)) {
				listaDeHorarios.getHorarios().remove(lhora);
				break;
			}
		}
	}

	/**
	 * Método que reemplaza un Objeto de tipo Turno por otro modificado, en una
	 * lista de Horarios.
	 */
	@Override
	public List<Turno> guardarCambios(Turno modificado) {
		// TODO Auto-generated method stub
		for (Turno lhora : listaDeHorarios.getHorarios()) {
			if (modificado.getDia().equals(lhora.getDia())) {
				lhora.setPaseador1(modificado.getPaseador1());
				lhora.setPaseador2(modificado.getPaseador2());
				lhora.setTurnoA(modificado.getTurnoA());
				lhora.setTurnoB(modificado.getTurnoB());
				break;
			}
		}
		List<Turno> listaOrdenada = new ArrayList<Turno>();
		for (int i = 1; i <= 6; i++) {
			for (Turno turno : listaDeHorarios.getHorarios()) {
				if (turno.getCod() == i) {
					listaOrdenada.add(turno);
				}
			}
		}
		return listaOrdenada;
	}

	/**
	 * Método busca coincidencias dentro del contenido de la pagina parámetro lo que
	 * se desea buscar tipo String.
	 */
	@Override
	public List<Turno> buscarPorNombre(String buscado) {
		// TODO Auto-generated method stub
		List<Turno> coincidenteList = new ArrayList<Turno>();
		for (Turno turno : listaDeHorarios.getHorarios()) {
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

	}

	@Override
	public void modificar(Turno turno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Turno turno) {
		// TODO Auto-generated method stub

	}

	@Override
	public Empleado getEmpleado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Empleado empleado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(Empleado empleado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Empleado empleado) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Empleado> getlistEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Empleado getEmpleado(Long cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turno> getLista(String dia) {
		// TODO Auto-generated method stub
		return null;
	}
}
