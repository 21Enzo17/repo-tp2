package ar.edu.unju.fi.Listas;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.entity.Autor;
import ar.edu.unju.fi.entity.Consejo;

import java.util.ArrayList;
@Component

public class ListaConsejo {

	private List <Consejo> listaConsejos;

	public ListaConsejo(List<Consejo> listaConsejos) {
		this.listaConsejos = listaConsejos;
		
	}
	
	public ListaConsejo() {
		listaConsejos = new ArrayList <Consejo>();
		listaConsejos.add(new Consejo());
	}

	@Override
	public String toString() {
		return "ListaConsejos [listaConsejos=" + listaConsejos + "]";
	}
	
	public List<Consejo> getListaConsejos() {
		return listaConsejos;
	}

	public void setListaConsejos(List<Consejo> listaConsejos) {
		this.listaConsejos = listaConsejos;
	}
}
