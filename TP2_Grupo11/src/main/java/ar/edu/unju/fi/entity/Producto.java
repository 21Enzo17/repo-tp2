package ar.edu.unju.fi.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Component
@Entity
@Table(name="PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name="cod")
    @Positive(message="El codigo debe ser mayor a 1")
    private Long cod;

    @Column(name="nombre")
    @NotBlank(message="El nombre no puede estar vacio")
    @Size(min=5,max=25,message = "El nombre debe ser mayor o igual a 5 caracteres y menor a 25")
    private String nombre;

    @Column(name="precio")
    @Positive(message="El precio no puede ser menor a 1")
    private float precio;
    
    @Column(name="descuento")
    @Positive(message="El descuento debe ser un valor positivo o cero")
    @Max(value=100,message="Solo puede tener un descuento de hasta 100%")
    private int descuento;
    
    @Column(name="imagen")
    private String imagen;

    @Autowired
    @JoinColumn(name="cat_id")
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @NotNull(message = "Debe seleccionar una categoria")
    private Categoria categoria;

    @Column(name="estado")
    private boolean estado;





    public Producto() {
    }


    public Producto(Long id, Long cod, String nombre, float precio, int descuento, String imagen, Categoria categoria, boolean estado) {
        this.id = id;
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
        this.imagen = imagen;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCod() {
        return this.cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto cod(Long cod) {

        setCod(cod);
        return this;
    }


    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto precio(float precio) {
        setPrecio(precio);

        return this;
    }

    public Producto descuento(int descuento) {
        setDescuento(descuento);
        return this;
    }

    public Producto imagen(String imagen) {
        setImagen(imagen);
        return this;
    }
    public Producto categoria(Categoria categoria) {
        setCategoria(categoria);
        return this;
    }

    public Producto estado(boolean estado) {
        setEstado(estado);
        return this;
    }

    /**
     * Este metodo permite calcular el desceunto total y retornar un precio
     * @return precio
     */
    public float calcularDescuento () {
        return this.precio - (this.precio * this.descuento)/100;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", cod='" + getCod() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", descuento='" + getDescuento() + "'" +
            "}";
    }
    
}
