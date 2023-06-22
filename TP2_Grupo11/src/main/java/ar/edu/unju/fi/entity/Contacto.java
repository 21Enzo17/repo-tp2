package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
@Component
@Entity
@Table(name="Contacto")
public class Contacto {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	    @Column(name = "nombre")
	    @NotBlank(message = "Debes ingresar el nombre de la provincia")
	    private String nombre;
	    @Column(name = "estado")
	    private boolean estado;
	    @Column(name= "email")
	    @NotBlank(message = "Debes ingresar un email valido")
	    private String email; 
	    @Column(name="ciudad")
	    @NotBlank(message = "Debes ingresar una ciudad")
	    private String ciudad;
	    @Column (name="mensaje")
	    @NotBlank(message = "Debes ingresar un mensaje")
	    private String mensaje;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCiudad() {
			return ciudad;
		}
		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
}
