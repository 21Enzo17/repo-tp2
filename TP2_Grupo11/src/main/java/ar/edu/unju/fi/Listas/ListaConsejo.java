package ar.edu.unju.fi.Listas;

import java.util.List;

import org.springframework.stereotype.Component;

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
		listaConsejos.add(new Consejo(1,"Al cuidar de tu mascota puedes mejorar tu salud física y mental","¿Sabías que la hormona Oxitocina, que se secreta cuando el cuerpo experimenta placer, también se secreta cuando una mascota y su dueño experimentan interacciones positivas entre si?\r\n. Entre más fuerte sea el vínculo entre la persona y su mascota, mayor es la posibilidad de que la persona secrete esta hormona, creando estados de felicidad y también de salud.\r\n Se encuentra comprobado que al tener una estrecha relación con tu mascota e interactuar con esta regularmente, aparecen una serie de beneficios en tu vida. Estudios demuestran que las personas que cuidan de sus mascotas también podrían ser más saludables, a razón que al sacarlos a caminar o correr y también jugar activamente con ellos se hace ejercicio teniendo un impacto positivo en su cuerpo y mente. En particular se han demostrado beneficios de salud al disminuir la tasa de problemas cardiovasculares."));
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
