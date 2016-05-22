package modelo;

import java.io.Serializable;

/**
 *
 * @author JenifferHR
 */
public class Usuario  implements Serializable{
    
    private String cedula;
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasenia;
    private String tipo;

    public Usuario() {
    }

    public Usuario(String cedula, String nombreCompleto, String nombreUsuario, String contrasenia, String tipo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getInformation() {
        return "Usuario{" + "cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", tipo=" + tipo + '}';
    }
}
