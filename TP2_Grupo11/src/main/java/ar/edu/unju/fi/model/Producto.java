package ar.edu.unju.fi.model;

public class Producto {
    private String nombre;
    private int cod;
    private float precio;
    private String categoria;
    private int descuento;
    private String imagen;



    public Producto() {
    }

    public Producto(String nombre, int cod, float precio, String categoria, int descuento, String imagen) {
        this.nombre = nombre;
        this.cod = cod;
        this.precio = precio;
        this.categoria = categoria;
        this.descuento = descuento;
        this.imagen = imagen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCod() {
        return this.cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto cod(int cod) {
        setCod(cod);
        return this;
    }

    public Producto precio(float precio) {
        setPrecio(precio);
        return this;
    }

    public Producto categoria(String categoria) {
        setCategoria(categoria);
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
   

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
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
    
    public boolean validarProducto(){
        return this.nombre.length()>0 && this.categoria.length()>0 && this.cod!=0 && this.precio!=0;
    }
}
