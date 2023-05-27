package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Dia;


public class DiasSemana {

	private List<Dia> semana;
	
	public DiasSemana() {
		
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

	public DiasSemana(List<Dia> semana) {
		super();
		this.semana = semana;
	}
	
}
