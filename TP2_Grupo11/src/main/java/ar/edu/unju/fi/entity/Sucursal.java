package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Sucursal {
    @NotBlank(message = "Debe ingresar una dirección")
    @Size(min = 5, message = "La dirección debe de ser mayor a 5 o igual carácteres")
    private String direccion;
    @NotBlank(message = "Debe ingresar un teléfono")
    @Size(min = 8, message = "El teléfono debe tener al menos 8 digitos")
   @Pattern(regexp = "[+]?(?:[1-9]\\d*|0)(?:\\s[1-9]\\d*)*", message = "El teléfono debe tener numeros del 1 al 9")
    private String telefono;
    @NotBlank(message = "Debe ingresar un mail")
    @Email(message = "Debe ingresar un mail válido")
    private String mail;
    @NotBlank(message = "Debe ingresar un horario de atención")
    @Size(min = 10, message = "Debes ingresar un horario más detallado (10 caracteres mínimo)")
    private String horarioAtencion;

    public Sucursal() {
    }

    public Sucursal(String direccion, String telefono, String mail, String horarioAtencion) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.horarioAtencion = horarioAtencion;
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

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                ", horarioAtencion='" + horarioAtencion + '\'' +
                '}';
    }


}
