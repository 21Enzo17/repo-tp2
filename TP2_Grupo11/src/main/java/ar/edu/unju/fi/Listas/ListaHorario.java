package ar.edu.unju.fi.Listas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Turno;

@Component
public class ListaHorario{
	
	private List<Turno> horarios;

    public ListaHorario(){
        horarios = new ArrayList<Turno>();
        //precarga de horarios
        horarios.add(new Turno((long) 1,"Lunes","9 a 11 hrs","15 a 19 hrs","juan Perez","Maria Juana"));
        horarios.add(new Turno((long) 2,"Martes","9 a 11 hrs","15 a 19 hrs","juan Perez","Maria Juana"));
        horarios.add(new Turno((long) 3,"Miércoles","9 a 11 hrs","15 a 19 hrs","juan Perez","Roberto Vasquez"));
        horarios.add(new Turno((long) 4,"Jueves","9 a 11 hrs","15 a 19 hrs","Lucas Días","Maria Juana"));
        horarios.add(new Turno((long) 5,"Viernes","9 a 11 hrs","15 a 19 hrs","juan Perez","Maria Juana"));
        horarios.add(new Turno((long) 6,"Sábado","9 a 11 hrs","15 a 19 hrs","johan Rosero","Maria Juana"));

    }

	public List<Turno> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Turno> horarios) {
		this.horarios = horarios;
	}
    
	public boolean existe (String dia) {
		boolean aux=false,band=true;
		for (int i = 0; i < horarios.size() && band; i++) {
		    if(horarios.get(i).getDia().equals(dia)) {
		    	aux=true;
		    	band=false;
		    }
		}
		return aux;
	}
}