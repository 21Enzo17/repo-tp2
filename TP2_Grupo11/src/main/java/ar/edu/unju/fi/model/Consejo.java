package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.*;

@Component
public class Consejo {
	@NotBlank(message="El titulo no puede estar vacío")
	@Size(min=5,max=25,message = "El nombre debe ser mayor a 5 caracteres")
	private String titulo;
	@NotBlank(message="La descripción no puede estar vacía")
	@Size(min=5,max=550,message = "El nombre debe ser mayor a 5 caracteres")
	private String descripcion;
	@NotNull(message="El valor de ID no puede estar vacio")
	@Min(value=1, message="El ID debe ser un numero igual a 1 o mayor")
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Consejo(int id, String titulo, String descripcion) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	public Consejo() {
		
	}
	
	
}
