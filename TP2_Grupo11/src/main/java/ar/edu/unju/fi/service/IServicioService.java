/**
 * 
 */
package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Dia;

import ar.edu.unju.fi.entity.Empleado;

import ar.edu.unju.fi.entity.Turno;

/**
 * @author JohanR 
 *Interface de Servicio para Servicio de Paseos.
 */
public interface IServicioService {
	
	List<Turno> getHorarios();
	List<Dia> getSemana();
	List<Turno> getListaOrdenada();
	List<String> getDisponibles();	
	List<Turno> guardarTurno(Turno formHorario);
	List<Turno> guardarCambios(Turno modificado);
	List<Empleado> getlistEmpleados();
	
	boolean existe(String dia);
	boolean semanaCompleta();
	void eliminarHorario(String dia);
	public Turno getTurno(String dia);
	
	public List<Turno> buscarPorNombre( String buscado);
	
	public Turno getTurno();
	public void guardar(Turno turno);
	public void modificar(Turno turno);
	public void eliminar(Turno turno);
		
	public Empleado getEmpleado();
	public void guardar(Empleado empleado);
	public void modificar(Empleado empleado);
	public void eliminar(Long id);
	void eliminar(Empleado empleado);
	public Empleado getEmpleado(Long cod);


}
