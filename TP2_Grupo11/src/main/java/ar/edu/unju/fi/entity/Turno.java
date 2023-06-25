package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component

@Entity
@Table(name="Turno")
public class Turno {

	@Id
	@Column(name="ID-TURNO")
	private Long cod;
	
	@Size(min=3,max=15)
	@Column(name="DIA")
	private String dia;
	
	@NotBlank(message="Debe ingresar turno, (ejm-09 a 12 hrs)")
	@Size(min=5,max=30)
	@Column(name="T.MAÑANA")
	private String turnoA;
	
	@NotBlank(message="Debe ingresar turno, (ejm-14 a 19 hrs)")
	@Size(min=5,max=30)
	@Column(name="T.TARDE")
	private String turnoB;
	
	@NotBlank(message="Debe ingresar un nombre")
	@Size(min=3,max=30)
	@Pattern(regexp="[a-zA-Z ]+",message="Solo debe contener letras")
	@Column(name="PASEADOR AM")
	private String paseador1;
	
	@NotBlank(message="Debe ingresar un nombre")
	@Size(min=3,max=30)
	@Pattern(regexp="[a-zA-Z ]+",message="Solo debe contener letras")
	@Column(name="PASEADOR PM")
	private String paseador2;
	
	@Column(name="ESTADO")
	private boolean estado;

	
	
	public Turno() {
		super();
	}

	public Turno(Long cod, String dia, String turnoA, String turnoB, String paseador1, String paseador2) {
		super();
		this.cod = cod;
		this.dia = dia;
		this.turnoA = turnoA;
		this.turnoB = turnoB;
		this.paseador1 = paseador1;
		this.paseador2 = paseador2;

		this.estado = true;
	}
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(long i) {
		this.cod = i;
	}

	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getTurnoA() {
		return turnoA;
	}
	public void setTurnoA(String turnoA) {
		this.turnoA = turnoA;
	}
	public String getTurnoB() {
		return turnoB;
	}
	public void setTurnoB(String turnoB) {
		this.turnoB = turnoB;
	}
	public String getPaseador1() {
		return paseador1;
	}
	public void setPaseador1(String paseador1) {
		this.paseador1 = paseador1;
	}
	public String getPaseador2() {
		return paseador2;
	}
	public void setPaseador2(String paseador2) {
		this.paseador2 = paseador2;
	}
	public void autoAsignarId(Turno turno){
		switch(turno.getDia()) {
        case "Lunes":
        	turno.setCod(1);
            break;
        case "Martes":
        	turno.setCod(2);
            break;
        case "Miércoles":
        	turno.setCod(3);
            break;
        case "Jueves":
        	turno.setCod(4);
            break;
        case "Viernes":
        	turno.setCod(5);
            break;
        case "Sábado":
        	turno.setCod(6);
            break;
        default:
        	turno.setCod(0);
        	}
		turno.setEstado(true);
	}

}
