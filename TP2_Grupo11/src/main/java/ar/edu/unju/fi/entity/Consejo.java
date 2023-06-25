package ar.edu.unju.fi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Component
@Entity
@Table(name="CONSEJO")
public class Consejo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo")
	@NotBlank(message="El titulo no puede estar vacío")
	@Size(min=5,max=25,message = "El titulo debe ser mayor a 5 caracteres y menor a 25 caracteres")
	private String titulo;
	
	@Column(name = "descripción")
	@NotBlank(message="La descripción no puede estar vacía")
	@Size(min=5,max=550,message = "La descripción debe ser mayor a 5 caracteres y menor a 500 caracteres")
	private String descripcion;
	
    @Autowired
    @JoinColumn(name="autor_id")
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @NotNull(message = "Debe seleccionar un autor")
	private Autor autor;
	
	@Column(name = "estado")
	private boolean estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Consejo(
			@NotNull(message = "El valor de ID no puede estar vacio") @Positive(message = "El ID debe ser un numero igual a 1 o mayor") Long id,
			@NotBlank(message = "El titulo no puede estar vacío") @Size(min = 5, max = 25, message = "El titulo debe ser mayor a 5 caracteres y menor a 25 caracteres") String titulo,
			@NotBlank(message = "La descripción no puede estar vacía") @Size(min = 5, max = 550, message = "La descripción debe ser mayor a 5 caracteres y menor a 500 caracteres") String descripcion,
			Autor autor, boolean estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.autor = autor;
		this.estado = estado;
	}

	public Consejo() {
		super();
	}
	
	
}
