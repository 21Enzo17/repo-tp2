package ar.edu.unju.fi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Entity
@Table(name = "SUCURSAL")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "direccion")
    @NotBlank(message = "Debe ingresar una dirección")
    @Size(min = 5, message = "La dirección debe de ser mayor a 5 o igual carácteres")
    private String direccion;
    @Column(name = "telefono")
    @NotBlank(message = "Debe ingresar un teléfono")
    @Size(min = 8, message = "El teléfono debe tener al menos 8 digitos")
    @Pattern(regexp = "[+]?(?:[1-9]\\d*|0)(?:\\s[1-9]\\d*)*", message = "El teléfono debe tener numeros del 1 al 9")
    private String telefono;
    @Column(name = "mail")
    @NotBlank(message = "Debe ingresar un mail")
    @Email(message = "Debe ingresar un mail válido")
    private String mail;
    @Column(name = "horario_inicio")
    @NotNull(message = "Debes ingresar un horario")
    //@PastOrPresent(message = "El horario no es el adecuado")
    private LocalTime horarioInicio;
    @Column(name = "horario_fin")
    @NotNull(message = "Debes ingresar un horario")
    //@FutureOrPresent(message = "El horario no es el adecuado")
    private LocalTime horarioFin;
    private boolean estado;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia_fk")
    @NotNull(message = "Debe seleccionar una provincia")
    private Provincia provi;

    public Sucursal() {
        this.estado = true;
    }

    public Sucursal(Long id, String direccion, String telefono, String mail, LocalTime horarioInicio, LocalTime horarioFin, boolean estado) {
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provincia getProvi() {
        return provi;
    }

    public void setProvi(Provincia provi) {
        this.provi = provi;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", horarioFin='" + horarioFin + '\'' +
                ", estado=" + estado +
                '}';
    }
}
