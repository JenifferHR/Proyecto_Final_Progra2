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
public class Metodos_Cursos_XML {
    /**
     *
     * @author JenifferHR
     */

        String arregloInformacionConsultada[] = new String[3];
        ArchivosCurso_XML archivosCurso_XML;
        public Metodos_Cursos_XML() {
            archivosCurso_XML= new ArchivosCurso_XML();

        }

        public void agregarCursos(String informacion[]) {
            Cursos temporal = new Cursos(informacion[0], informacion[1], Integer.parseInt(informacion[2]),informacion[3]);

            archivosCurso_XML.guardarEnXML(temporal);

        }

        public boolean consultarCursos(String sigla) {
            boolean existe = false;

            existe = archivosCurso_XML.consultarInformacionDelXml(sigla);//devuelve true 0 false
            if (existe) {
                arregloInformacionConsultada[0] = archivosCurso_XML.getArregloInformacion()[1];
                arregloInformacionConsultada[1] = archivosCurso_XML.getArregloInformacion()[2];
                arregloInformacionConsultada[2] = archivosCurso_XML.getArregloInformacion()[3];
            }
            return existe;

        }

        public void modificarCursos(String arreglo[]) {
            archivosCurso_XML.modificarInformacionDelXml(arreglo);
        }

        //elimina el estudiante que tenga la cedula q le ingresa por parametros, mediante el arreglo
        public void eliminarEstudiante(String arreglo[]) {

            archivosCurso_XML.eliminarInformacionDelXml(arreglo[0]);

        }

        public String[] getArregloInformacion() {
            return this.arregloInformacionConsultada;
        }

}
