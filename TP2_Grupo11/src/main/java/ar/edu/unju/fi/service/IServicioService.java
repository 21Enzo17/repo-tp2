/**
 * 
 */
package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Dia;
import ar.edu.unju.fi.model.Turno;

/**
 * @author JohanR 
 *Interface de Servicio para Servicio de Paseos.
 */
public interface IServicioService {
	
	List<Turno> getHorarios();
	
	boolean existe(String dia);
	
	List<Dia> getSemana();
	
	List<Turno> getListaOrdenada();
	
	Turno getTurno();
	
	List<String> getDisponibles();	
	
	boolean semanaCompleta();
	
	List<Turno> guardarTurno(Turno formHorario);
		
	Turno getTurno(String dia);
	
	void eliminarHorario(String dia);
	
	List<Turno> guardarCambios(Turno modificado);
	
	ModelAndView buscarPorNombre(@RequestParam("nombre") String buscado, Model model);
}
