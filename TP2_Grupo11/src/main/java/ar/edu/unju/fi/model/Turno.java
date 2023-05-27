package ar.edu.unju.fi.model;


public class Turno {

	private int cod;
	private String dia;
	private String turnoA;
	private String turnoB;
	private String paseador1;
	private String paseador2;
	
	
	public Turno() {
		super();
	}
	
	public Turno(int cod, String dia, String turnoA, String turnoB, String paseador1, String paseador2) {
		super();
		this.cod = cod;
		this.dia = dia;
		this.turnoA = turnoA;
		this.turnoB = turnoB;
		this.paseador1 = paseador1;
		this.paseador2 = paseador2;
	}
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
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
