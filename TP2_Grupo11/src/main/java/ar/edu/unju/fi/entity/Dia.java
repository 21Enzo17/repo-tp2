package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;


import jakarta.persistence.Column;

@Component
public class Dia {
	
	@Column(name="dia")
    String nombre;
	@Column(name="id_dia")
	long cod;
	
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

	public long getCod() {

		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
}
