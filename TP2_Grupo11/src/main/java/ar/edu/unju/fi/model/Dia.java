package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Dia {

String nombre;
	int cod;
	
	public Dia() {
		super();
	}
	public Dia(String nombre, int cod) {
		super();
		this.nombre = nombre;
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
}
