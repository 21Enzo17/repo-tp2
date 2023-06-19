package ar.edu.unju.fi.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table
public class Empleado {

	@Id
	@Column
	@GeneratedValue()
	private Long cod;
	
	@NotBlank(message="Debe ingresar Nombre")
	@Size(min=3,max=15)
	@Column
	private String nombre;
	
	@NotBlank(message="Debe ingresar Apellido")
	@Size(min=3,max=30)
	@Column
	private String apellido;

	@Column
	private int edad;
	
	@Column
	private long dni;
	
	@Column
	private Date fecha_nacimiento;
	
	@NotBlank(message="Debe ingresar correo electronico")
	@Size(min=3,max=30)
	@Pattern(regexp="[a-zA-Z ]+",message="Solo debe contener letras")
	@Column
	private String email;
	
	@NotBlank(message="Debe ingresar direccion")
	@Size(min=3,max=30)
	@Column
	private String direccion;
	
	@NotBlank(message="Debe ingresar especialidad")
	@Size(min=3,max=30)
	@Column
	private String especialidad ;
	
	@Column
	private boolean estado;
		

	public Empleado() {
		super();
	}

	public Empleado(Long cod, @NotBlank(message = "Debe ingresar Nombre") @Size(min = 3, max = 15) String nombre,
			@NotBlank(message = "Debe ingresar Apellido") @Size(min = 3, max = 30) String apellido, int edad, long dni,
			Date fecha_nacimiento,
			@NotBlank(message = "Debe ingresar correo electronico") @Size(min = 3, max = 30) @Pattern(regexp = "[a-zA-Z ]+", message = "Solo debe contener letras") String email,
			@NotBlank(message = "Debe ingresar direccion") @Size(min = 3, max = 30) String direccion,
			@NotBlank(message = "Debe ingresar especialidad") @Size(min = 3, max = 30) String especialidad) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
		this.email = email;
		this.direccion = direccion;
		this.especialidad = especialidad;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
