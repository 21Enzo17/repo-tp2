package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "AUTOR")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autor_id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "estado")
	private boolean estado;
	
    @OneToMany(mappedBy = "autor")
    private List<Consejo> consejos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Autor(Long id, String nombre, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}
	public Autor() {
		super();
	}
	
	

	
}
