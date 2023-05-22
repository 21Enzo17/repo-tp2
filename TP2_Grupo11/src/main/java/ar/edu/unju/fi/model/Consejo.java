package ar.edu.unju.fi.model;

public class Consejo {
	private String titulo;
	private String descripcion;
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
