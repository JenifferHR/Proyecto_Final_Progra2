/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author JenifferHR
 */
public class Metodos_Estudiante_XML {
    
    String arregloInformacionConsultada[] = new String[2];
    Archivos_Estudiante_XML archivoEstudiante;

    public Metodos_Estudiante_XML() {
        archivoEstudiante = new Archivos_Estudiante_XML();

    }

    public void agregarEstudiante(String informacion[]) {
        Estudiante temporal = new Estudiante(informacion[0], informacion[1], informacion[2]);
        
        archivoEstudiante.guardarEnXML(temporal);

    }


    public boolean consultarEstudiante(String cedula) {
        boolean existe = false;
        
       existe=archivoEstudiante.consultarInformacionDelXml(cedula);//devuelve true 0 false
       if(existe)
       {
           arregloInformacionConsultada[0]=archivoEstudiante.getArregloInformacion()[1];
           arregloInformacionConsultada[1]=archivoEstudiante.getArregloInformacion()[2];
       }
        return existe;

    }

    public void modificarEstudiante(String arreglo[]) {
        archivoEstudiante.modificarInformacionDelXml(arreglo);
    }

    //elimina el estudiante que tenga la cedula q le ingresa por parametros, mediante el arreglo
    public void eliminarEstudiante(String arreglo[]) {
        
        archivoEstudiante.eliminarInformacionDelXml(arreglo[0]);
            
    }

    public String[] getArregloInformacion() {
        return this.arregloInformacionConsultada;
    }

   
}
