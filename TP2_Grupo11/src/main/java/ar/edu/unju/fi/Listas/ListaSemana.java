
package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Dia;

@Component
public class ListaSemana {

	private List<Dia> semana;
	
	public ListaSemana() {
		
		semana = new ArrayList<Dia>(); 
		semana.add(new Dia("Lunes",1));
		semana.add(new Dia("Martes",2));
		semana.add(new Dia("Miércoles",3));
		semana.add(new Dia("Jueves",4));
		semana.add(new Dia("Viernes",5));
		semana.add(new Dia("Sábado",6));					
	}

	public List<Dia> getSemana() {
		return semana;
	}

	public void setSemana(List<Dia> semana) {
		this.semana = semana;
	}

	public ListaSemana(List<Dia> semana) {
		super();
		this.semana = semana;
	}
	
}

