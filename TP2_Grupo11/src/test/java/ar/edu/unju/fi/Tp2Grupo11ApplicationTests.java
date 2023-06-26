package ar.edu.unju.fi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.entity.Turno;
import ar.edu.unju.fi.service.IServicioService;

@SpringBootTest
class Tp2Grupo11ApplicationTests {
	
	@Autowired
	@Qualifier("ServicioServiceMySql")
	IServicioService sevicio;
	
	@Autowired
	Turno turno;
	
	@Test
	void contextLoads() {
		
		turno.setCod(1);
		turno.setDia("Viernes");
		turno.setPaseador1("Juan Perez");
		turno.setEstado(true);
		turno.setPaseador2("Rocio Belen");
		turno.setTurnoA("12 a 15 hrs");
		turno.setTurnoB("14 a 18 hrs");
		sevicio.guardar(turno);
	}

}
