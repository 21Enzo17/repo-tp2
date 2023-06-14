package ar.edu.unju.fi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "PROVINCIA")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    @NotBlank(message = "Debes ingresar el nombre de la provincia")
    private String nombre;
    @Column(name = "estado")
    private boolean estado;

    @OneToMany(mappedBy = "provi", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Sucursal> listaSucursal;

    public Provincia() {
        this.estado = true;
    }

    public Provincia(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estado = true;
    }

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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }
}
