package ar.edu.unju.fi.model;


public class Turno {

	private String dia;
	private String turnoA;
	private String turnoB;
	private String paseador1;
	private String paseador2;
	
	
	public Turno() {
		super();
	}
	public Turno(String dia, String turnoA, String turnoB, String paseador1, String paseador2) {
		super();
		this.dia = dia;
		this.turnoA = turnoA;
		this.turnoB = turnoB;
		this.paseador1 = paseador1;
		this.paseador2 = paseador2;
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
}
