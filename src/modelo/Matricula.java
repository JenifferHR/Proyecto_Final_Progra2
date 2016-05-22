package modelo;

import java.io.Serializable;

/**
 *
 * @author tecnologiamultimedia
 */
public class Matricula  implements Serializable{
    
    private String codigo;
    private String cedula;
    private String sigla;
    boolean estado=false; //Todas las matriculas lo van a tener en false, cuando se elimina, se pasan a true.

    public Matricula(String codigo, String cedula) {
        this.codigo = codigo;
        this.cedula = cedula;
    }
    public Matricula(String codigo, String cedula, String sigla, boolean estado) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.sigla = sigla;
        this.estado=estado;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }
    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String infoMatricula() {
        return this.codigo+this.cedula+this.sigla;
    }
    public String getInformacion()
    {
        return this.codigo+this.cedula;
    }
}
