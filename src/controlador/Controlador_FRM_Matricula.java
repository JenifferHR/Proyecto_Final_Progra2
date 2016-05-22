package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Archivo_Matricula_XML;
import modelo.ArchivosCurso_XML;
import modelo.Archivos_Estudiante_XML;
import modelo.Conexion_BD;
import modelo.Matricula;
import modelo.MetodosCursos;
import modelo.MetodosEstudiantes;
import modelo.MetodosMatricula;
import modelo.MetodosUsuarios;
import modelo.Metodos_Cursos_XML;
import modelo.Metodos_Estudiante_XML;
import vista.FRM_AlmacenarInformacion;

import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_Matricula;

/**
 *
 * @author tecnologiamultimedia
 */
public class Controlador_FRM_Matricula implements ActionListener {

    FRM_Matricula frm_matricula;
    Conexion_BD conexion_BD;
    public MetodosMatricula metodosMatriculaDat;
    boolean encontroEstudiante = false;
    boolean encontroCurso = false;
    private Metodos_Cursos_XML metodos_Cursos_XML;
    private Metodos_Estudiante_XML metodos_Estudiante_XML;
    private Archivo_Matricula_XML archivo_Matricula_XML;
    private Archivos_Estudiante_XML archivos_Estudiante_XML;
    FRM_MantenimientoCursos fRM_MantenimientoCursos;
    FRM_MantenimientoEstudiantes fRM_MantenimientoEstudiantes;
    MetodosCursos metodosCursosDat;
    MetodosEstudiantes metodosEstudianteDat;

    public Controlador_FRM_Matricula(FRM_MantenimientoCursos fRM_MantenimientoCursos, FRM_MantenimientoEstudiantes fRM_MantenimientoEstudiantes, FRM_Matricula frm_matricula) {

        this.fRM_MantenimientoCursos = fRM_MantenimientoCursos;
        this.fRM_MantenimientoEstudiantes = fRM_MantenimientoEstudiantes;
        this.frm_matricula = frm_matricula;

        if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
            
            metodosCursosDat = new MetodosCursos();
           // metodosMatriculaDat = new MetodosMatricula();
            metodosEstudianteDat = new MetodosEstudiantes();
            metodosMatriculaDat= new MetodosMatricula(metodosEstudianteDat, metodosCursosDat, this, frm_matricula);
        }

        if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) {
            this.conexion_BD = new Conexion_BD();

        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
            metodos_Cursos_XML = new Metodos_Cursos_XML();
            metodos_Estudiante_XML = new Metodos_Estudiante_XML();
            archivo_Matricula_XML = new Archivo_Matricula_XML();
            archivos_Estudiante_XML = new Archivos_Estudiante_XML();

        }
    }

    public String codigo() {
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
            return metodosMatriculaDat.devolverCodigo();
        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) {
            return conexion_BD.devolverCodigo();
        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
            return archivo_Matricula_XML.obtenerCodigo();
        }
        return "";
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("ConsultaRapidaCedula")) {

            if (!frm_matricula.devolverCedula().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    if (metodosEstudianteDat.consultarEstudiante(frm_matricula.devolverCedula())) {
                        frm_matricula.colocarNombreEstudiante(metodosEstudianteDat.getArregloInformacion()[0]);
                    } else {
                        frm_matricula.mostrarMensaje("Cedula No Registrada");
                    }
                }//fin del num 1
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
                {
                    if (conexion_BD.consultarEstudiante(frm_matricula.devolverCedula())) {
                        frm_matricula.colocarNombreEstudiante(conexion_BD.devolverInformacionEstudiante()[0]);
                    } else {
                        frm_matricula.mostrarMensaje("Cedula No Registrada");
                    }
                }
                // parte para xml
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
                    if (archivos_Estudiante_XML.consultarInformacionDelXml(frm_matricula.devolverCedula())) {
                        frm_matricula.colocarNombreEstudiante(archivos_Estudiante_XML.getArregloInformacion()[1]);
                    } else {
                        frm_matricula.mostrarMensaje("Cedula No Registrada");
                    }
                }//fin de xml
            } else {
                frm_matricula.mostrarMensaje("Digite Una Cedula");
            }
        }// fin del consulta rapida cedula

        if (e.getActionCommand().equals("ConsultaRapidaSigla")) {
            if (!frm_matricula.devolverSigla().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    if (metodosCursosDat.consultarCurso(frm_matricula.devolverSigla())) {
                        frm_matricula.colocarNombreCurso(metodosCursosDat.getArregloInformacion()[0]);
                    } else {
                        frm_matricula.mostrarMensaje("Sigla No Registrada");
                        frm_matricula.colocarNombreCurso("");
                    }
                }//fin del num 1
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
                {
                    if (conexion_BD.consultarCurso(frm_matricula.devolverSigla())) {
                        frm_matricula.colocarNombreCurso(conexion_BD.devolverInformacionCurso()[0]);
                    } else {
                        frm_matricula.mostrarMensaje("Sigla No Registrada");
                        frm_matricula.colocarNombreCurso("");
                    }
                }
                // parte para xml
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
                    if (metodos_Cursos_XML.consultarCursos(frm_matricula.devolverSigla())) {
                        frm_matricula.colocarNombreCurso(metodos_Cursos_XML.getArregloInformacion()[0]);
                    } else {
                        frm_matricula.mostrarMensaje("Sigla No Registrada");
                        frm_matricula.colocarNombreCurso("");
                    }
                }
            } else {
                frm_matricula.mostrarMensaje("Digite Una Sigla");
            }
        }//fin del consulta rapida sigla

        if (e.getActionCommand().equals("Agregar")) {
            if (!frm_matricula.devolverSigla().equals("") && !frm_matricula.devolverNombreCurso().equals("")) {

                frm_matricula.agregarInformacionTabla();
                frm_matricula.colocarNombreCurso("");
                frm_matricula.colocarSigla("");

            } else {
                frm_matricula.mostrarMensaje("Ingrese una sigla y un Codigo de Matricula");
            }
        }

        if (e.getActionCommand().equals("Finalizar")) {
            ArrayList<String> listaSiglas = new ArrayList<>();
            for (int contador = 0; contador < frm_matricula.getCantidadFilas(); contador++) {
                listaSiglas.add(frm_matricula.devolverDato(contador, 3));
            }
            if (!frm_matricula.devolverCodigo().equals("") && !frm_matricula.devolverNombreEstudiante().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    if (!metodosMatriculaDat.consultarMatricula(frm_matricula.devolverCodigo())) {
                    String arreglo[] = new String[3];
                    for (int i = 0; i < frm_matricula.getCantidadFilas(); i++) {
                        arreglo[0] = frm_matricula.devolverCodigo();
                        arreglo[1] = frm_matricula.devolverDato(i, 1);
                        arreglo[2] = frm_matricula.devolverDato(i, 3);
                        metodosMatriculaDat.agregarMatricula(arreglo);
                    }
                    metodosMatriculaDat.guardarArchivo();
                    frm_matricula.limpiarTabla();
                    frm_matricula.colocarCodigo(metodosMatriculaDat.devolverCodigo());
                    frm_matricula.resetearVentana();
                    encontroEstudiante = true;
                    encontroCurso = true;
                    }
                    else {
                        frm_matricula.limpiarTabla();
                        frm_matricula.resetearVentana();
                        frm_matricula.colocarCodigo(metodosMatriculaDat.devolverCodigo());
                        frm_matricula.mostrarMensaje("Este Matricula Ya Esta Registrada");
                    }
                }//fin del num 1
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
                {
                    if (!conexion_BD.consultarMatricula(frm_matricula.devolverCodigo())) {
                        String arreglo[] = new String[3];
                        for (int contador = 0; contador < frm_matricula.getCantidadFilas(); contador++) {
                            arreglo[0] = frm_matricula.devolverCodigo();
                            arreglo[1] = frm_matricula.devolverDato(contador, 1);
                            arreglo[2] = frm_matricula.devolverDato(contador, 3);
                            conexion_BD.registrarMatricula(arreglo);

                        }
                        frm_matricula.mostrarMensaje("Matricula Registrada");
                        frm_matricula.resetearVentana();
                        frm_matricula.colocarCodigo(conexion_BD.devolverCodigo());

                        encontroEstudiante = false;
                        encontroCurso = false;
                        frm_matricula.limpiarTabla();

                    } else {
                        frm_matricula.mostrarMensaje("Este Matricula Ya Esta Registrada");
                    }

                }// Fin bases de datos
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    Matricula matricula = new Matricula(frm_matricula.devolverCodigo(), frm_matricula.devolverCedula());
                    if (!archivo_Matricula_XML.consultarInformacionDelXml(frm_matricula.devolverCodigo())) {
                        if (archivo_Matricula_XML.guardarEnXML(matricula, listaSiglas)) {
                            frm_matricula.mostrarMensaje("Matricula Guardada");
                        } else {
                            frm_matricula.mostrarMensaje("No se pudo registrar la matricula");
                        }
                        frm_matricula.resetearVentana();
                        encontroEstudiante = false;
                        encontroCurso = false;
                        frm_matricula.limpiarTabla();
                        this.frm_matricula.colocarCodigo(archivo_Matricula_XML.obtenerCodigo());
                    } else {
                        frm_matricula.mostrarMensaje("Este Codigo de Matricula Ya Se Encuentra Registrado");
                    }
                }// Fin de la opcion 3
            } else {
                frm_matricula.mostrarMensaje("Ingrese un codigo y un estudiante");
            }

        }// Fin de finalizar

        if (e.getActionCommand().equals("Consultar")) {
            if (!frm_matricula.devolverCodigo().trim().equals("")) {
                frm_matricula.limpiarTabla();
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se  muestra la matricula del Archivo .dat
                {frm_matricula.limpiarTabla();
                    if (metodosMatriculaDat.consultarMatricula(frm_matricula.devolverCodigo())) {
                        frm_matricula.colocarCedula(metodosMatriculaDat.getArregloInformacion()[1]);
                        frm_matricula.habilitarEdicion();
                        metodosEstudianteDat.consultarEstudiante(frm_matricula.devolverCedula());
                        frm_matricula.colocarNombreEstudiante(metodosEstudianteDat.getArregloInformacion()[0]);
                        frm_matricula.colocarSigla(metodosMatriculaDat.getArregloInformacion()[2]);
                        metodosCursosDat.consultarCurso(frm_matricula.devolverSigla());
                        frm_matricula.colocarNombreCurso(metodosCursosDat.getArregloInformacion()[0]);
                       
                    } else {
                        frm_matricula.habilitarAgregar();
                        frm_matricula.mostrarMensaje("Matricula No Registrada");
                    }
                }//fin del num 1
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se muestra la matricula de  la bases de datos
                {
                    if (conexion_BD.consultarMatricula(frm_matricula.devolverCodigo())) {
                        frm_matricula.colocarCedula(conexion_BD.getCedulaEncontrada());
                        frm_matricula.habilitarEdicion();
                        frm_matricula.colocarNombreEstudiante(conexion_BD.devolverInformacionEstudiante()[0]);
                        frm_matricula.cargarInformacionTabla(conexion_BD.getListaSiglas(), conexion_BD.devolverInformacionEstudiante()[0], frm_matricula.devolverCodigo(), conexion_BD.getCedulaEncontrada());
                    } else {
                        frm_matricula.habilitarAgregar();
                        frm_matricula.mostrarMensaje("Matricula No Registrada");
                    }

                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se muestra la matricula XML
                {

                    if (archivo_Matricula_XML.consultarInformacionDelXml(frm_matricula.devolverCodigo())) {
                        frm_matricula.colocarCedula(archivo_Matricula_XML.getArregloInformacion()[1]);
                        metodos_Estudiante_XML.consultarEstudiante(frm_matricula.devolverCedula());
                        frm_matricula.colocarNombreEstudiante(metodos_Estudiante_XML.getArregloInformacion()[0]);
                        if (archivo_Matricula_XML.getListaSiglas().size() > 0) {

                            frm_matricula.deshabilitarInicio();
                            frm_matricula.habilitarEdicion();
                            frm_matricula.limpiarTabla();
                            frm_matricula.cargarInformacionTabla(archivo_Matricula_XML.getListaSiglas(), frm_matricula.devolverNombreEstudiante(), frm_matricula.devolverCodigo(), frm_matricula.devolverCedula());
                        } else {
                            frm_matricula.cargarInformacionTabla(archivo_Matricula_XML.getListaSiglas(), null, null, null);
                        }
                    } else {
                        frm_matricula.mostrarMensaje("No Se Encontro La Matricula");
                        frm_matricula.limpiarSigla();
                        frm_matricula.limpiarTabla();
                        frm_matricula.habilitarAgregar();
                    }
                }

            }

        }

        if (e.getActionCommand().equals("Modificar")) {

            frm_matricula.mostrarMensaje("Ninguna Matricula Puede Ser Modificada");

        }

        if (e.getActionCommand()
                .equalsIgnoreCase("Eliminar")) {
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
            {
                int filaSeleccionada = frm_matricula.devolverFilaSeleccionada();
                if (filaSeleccionada < 0) {
                    frm_matricula.mostrarMensaje("Seleccione una Fila");
                } else //Trae el codigo y la sigla del curso que va eliminar en la tabla de la ventana matricula
                {
                    String codigo = frm_matricula.devolverDato(filaSeleccionada, 0);
                    String sigla = frm_matricula.devolverDato(filaSeleccionada, 3);
                    metodosMatriculaDat.eliminarMatriculaPorCurso(codigo, sigla);
                    metodosMatriculaDat.guardarArchivo();
                    frm_matricula.mostrarMensaje("Matricula Eliminada");
                    frm_matricula.resetearVentana();
                }
            }//fin del num 1
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se elimina a bases de datos
            {
                if (conexion_BD.consultarMatricula(frm_matricula.devolverCodigo())) {
                    if (frm_matricula.devoverDatosSeleccionados() != null) {
                        if (conexion_BD.eliminarDetalleMatricula(frm_matricula.devolverCodigo(), frm_matricula.devoverDatosSeleccionados())) {
                            frm_matricula.mostrarMensaje("Se elimino corrctamente");
                            frm_matricula.resetearVentana();
                        }
                    } else {
                        frm_matricula.mostrarMensaje("Seleccione la fila que desea eliminar");
                    }

                } else {
                    frm_matricula.mostrarMensaje("No existe la matricula");
                }
            }
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
            {
                if (archivo_Matricula_XML.consultarInformacionDelXml(frm_matricula.devolverCodigo())) {
                    //esta variable se utiliza para guardar la opcion que trae el metodo opcion seleccionada
                    String seleccion=frm_matricula.opcionSeleccionada();
                    //se compara lo que devuelve el metodo
                    if(seleccion.equalsIgnoreCase("1")||seleccion.equalsIgnoreCase("uno"))
                    {
                        archivo_Matricula_XML.eliminarInformacionDelXml(frm_matricula.devolverCodigo());
                        frm_matricula.mostrarMensaje("Matricula Eliminada");
                        frm_matricula.limpiarSigla();
                        frm_matricula.colocarNombreCurso("");
                        frm_matricula.colocarCedula("");
                        frm_matricula.colocarNombreEstudiante("");
                        frm_matricula.limpiarTabla();
                    }
                    else
                    {
                        frm_matricula.limpiarTabla();
                        frm_matricula.limpiarSigla();
                        frm_matricula.resetearVentana();
                    }
                } else {
                    frm_matricula.mostrarMensaje("El Codigo De Matricula No existe");
                }
            }

        }

        verificarConsultas();
    }

    public void guardarArchivo() {
        metodosMatriculaDat.guardarArchivo();
    }

    public void verificarConsultas() {
        if (encontroEstudiante && encontroCurso) {
            this.frm_matricula.habilitarAgregar();
        }
    }

    public String devolverCod() {
        return "";//conexion_BD.devolverCodigo();
    }

}
