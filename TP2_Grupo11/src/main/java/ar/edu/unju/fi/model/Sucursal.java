package ar.edu.unju.fi.model;

public class Sucursal {
    private String direccion;
    private String telefono;
    private String mail;
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
