package ar.edu.unju.fi.model;

public class Consejo {
	private String titulo;
	private String descripcion;
	
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
	
	public Consejo(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	public Consejo() {
		
	}
	
	
}
