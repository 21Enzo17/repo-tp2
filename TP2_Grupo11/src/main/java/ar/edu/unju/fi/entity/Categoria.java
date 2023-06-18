package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Component
@Entity
@Table(name="CATEGORIAS")
public class Categoria {
    
    @Id
    @Column(name="cat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cat_nombre")
    @Size(min=5,max=15, message="Debe contener entre 5 y 15 caracteres")
    private String nombre;
    
    @Column(name="estado")
    private boolean estado;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria() {
    }

    public Categoria(Long id, String nombre, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Categoria id(Long id) {
        setId(id);
        return this;
    }

    public Categoria nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Categoria estado(boolean estado) {
        setEstado(estado);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }

   
    

}
