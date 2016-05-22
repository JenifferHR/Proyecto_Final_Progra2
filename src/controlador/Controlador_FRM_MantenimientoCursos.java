package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ArchivosCurso_XML;
import modelo.ArchivosCursos;
import modelo.Conexion_BD;
import modelo.MetodosCursos;
import modelo.Metodos_Cursos_XML;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_MantenimientoCursos;

/**
 *
 * @author JenifferHR
 */
public class Controlador_FRM_MantenimientoCursos implements ActionListener {

    //Se crean las referencias necesarias
    FRM_MantenimientoCursos frm_mantenimientoCursos;
    private Metodos_Cursos_XML metodos_Cursos_XML;
    private ArchivosCurso_XML archivosCurso_XML;
    private ArchivosCursos archivosCursos;
    private MetodosCursos metodosCursos;
    public Conexion_BD conexion_BD;

    public Controlador_FRM_MantenimientoCursos(FRM_MantenimientoCursos frm_mantenimientoCursos) {
        this.frm_mantenimientoCursos = frm_mantenimientoCursos;
        archivosCurso_XML = new ArchivosCurso_XML();
        metodos_Cursos_XML = new Metodos_Cursos_XML();
        archivosCursos = new ArchivosCursos();
        metodosCursos = new MetodosCursos();
        conexion_BD = new Conexion_BD();
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals("Agregar")) { 
            if (verificarEspacios()) { //este metodo es creado al final de esta clase
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    //se llama al metodo agregar y le entra toda la informacion que ingreso el usuario en la ventana
                    metodosCursos.agregarCurso(frm_mantenimientoCursos.devolverInformacion());
                    metodosCursos.guardarArchivo(); //se procede a guardar dentro del archivo
                    frm_mantenimientoCursos.mostrarMensaje("El curso fue registrado de forma correcta");
                    frm_mantenimientoCursos.resetearGUI(); 
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                {
                    conexion_BD.registrarCurso(frm_mantenimientoCursos.devolverInformacion());
                    frm_mantenimientoCursos.mostrarMensaje("El estudiante fue registrado de forma correcta");
                    frm_mantenimientoCursos.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {

                    if (!metodos_Cursos_XML.consultarCursos(frm_mantenimientoCursos.devolverSigla())) {
                        metodos_Cursos_XML.agregarCursos(frm_mantenimientoCursos.devolverInformacion());
                        frm_mantenimientoCursos.mostrarMensaje("Curso Agregado");
                        frm_mantenimientoCursos.resetearGUI();
                    } else {
                        frm_mantenimientoCursos.mostrarMensaje("Este Curso Ya se encuentra agregado");
                    }
                }
            }
        }
        if (evento.getActionCommand().equals("Consultar") || evento.getActionCommand().equals("ConsultaRapida")) {

            if (!frm_mantenimientoCursos.devolverSigla().trim().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    buscar(); //llama el metodo que se encuentra creado al final de esta clase
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                {
                    buscarBasesDatos(); 
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    //se llama al metodo consultarCursos que le ingresa por parametros la sigla a consultar
                    if (metodos_Cursos_XML.consultarCursos(frm_mantenimientoCursos.devolverSigla())) {
                        //este metodo muestra en la ventana, la informacion que el usuario solicito
                        frm_mantenimientoCursos.mostrarInformacion(metodos_Cursos_XML.getArregloInformacion());
                        frm_mantenimientoCursos.habilitarEdicion();
                    } else { 
                        frm_mantenimientoCursos.mostrarMensaje("El Curso no existe");
                        frm_mantenimientoCursos.habilitarAgregar();
                    }
                }
            } else {
                frm_mantenimientoCursos.mostrarMensaje("Debe digitar una sigla");
            }

        }
        if (evento.getActionCommand().equals("Modificar")) {
            if (verificarEspacios()) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    /*
                    se llama al metodo modificar curso al cual le ingresa por parametros
                    un vector con la informacion que se encuentra en la ventana cursos
                    para proceder a modificar
                    */
                    metodosCursos.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                    frm_mantenimientoCursos.mostrarMensaje("El curso fue modificado de forma correcta.");
                    frm_mantenimientoCursos.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                {
                    conexion_BD.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                    frm_mantenimientoCursos.mostrarMensaje("El curso fue modificado de forma correcta.");
                    frm_mantenimientoCursos.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    if (metodos_Cursos_XML.consultarCursos(frm_mantenimientoCursos.devolverSigla())) {
                        metodos_Cursos_XML.modificarCursos(frm_mantenimientoCursos.devolverInformacion());
                        frm_mantenimientoCursos.mostrarMensaje("El curso fue modificado de forma correcta.");
                        frm_mantenimientoCursos.resetearGUI();
                    } else {
                        frm_mantenimientoCursos.mostrarMensaje("La Sigla no se encontro");
                    }
                }
            }
        }
        if (evento.getActionCommand().equals("Eliminar")) {
            if (!frm_mantenimientoCursos.devolverSigla().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    /*
                    se llama al metodo modificar curso al cual le ingresa por parametros
                    la informacion almacenada en el vector con toda la informacion
                    que se encuentra en la ventana cursos para proceder a eliminar
                    */
                    metodosCursos.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                    frm_mantenimientoCursos.mostrarMensaje("El curso fue eliminado de forma correcta.");
                    frm_mantenimientoCursos.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                {
                    conexion_BD.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                    frm_mantenimientoCursos.mostrarMensaje("El Curso fue eliminado de forma correcta.");
                    frm_mantenimientoCursos.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    if (metodos_Cursos_XML.consultarCursos(frm_mantenimientoCursos.devolverSigla())) {
                        metodos_Cursos_XML.eliminarEstudiante(frm_mantenimientoCursos.devolverInformacion());
                        frm_mantenimientoCursos.mostrarMensaje("El Curso fue eliminado de forma correcta.");

                        frm_mantenimientoCursos.resetearGUI();
                    } else {
                        frm_mantenimientoCursos.mostrarMensaje("La sigla no se encontro");
                    }
                }
            }//fin de verificacion de espacios
            else {
                frm_mantenimientoCursos.mostrarMensaje("Debe de ingresar una sigla");
            }
        }//fin de eliminar

    }//fin del ActionPerformed
    //Metodos que permite buscar en los archivos.dat

    public void buscar() {
        if (metodosCursos.consultarCurso(frm_mantenimientoCursos.devolverSigla())) {
            frm_mantenimientoCursos.mostrarInformacion(metodosCursos.getArregloInformacion());
            frm_mantenimientoCursos.habilitarEdicion();
        } else {
            frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
            frm_mantenimientoCursos.habilitarAgregar();
        }
    }

    //este metodo permite buscar en la base de datos
    public void buscarBasesDatos() {
        if (conexion_BD.consultarCurso(frm_mantenimientoCursos.devolverSigla())) {
            frm_mantenimientoCursos.mostrarInformacion(conexion_BD.devolverInformacionCurso());
            frm_mantenimientoCursos.habilitarEdicion();
        } else {
            frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
            frm_mantenimientoCursos.habilitarAgregar();
        }
    }

    //este metodo verifica que no se encuentren vacios los campos nombre y sigla
    public boolean verificarEspacios() {
        //Método .trim que elimina los caracteres blancos iniciales y finales de la cadena
        if (!frm_mantenimientoCursos.devolverInformacion()[0].trim().equals("")
                && !frm_mantenimientoCursos.devolverInformacion()[1].trim().equals("")
                && !frm_mantenimientoCursos.devolverInformacion()[0].trim().equals("")) {
            return true;
        } else {
            frm_mantenimientoCursos.mostrarMensaje("Complete los datos");
            return false;
        }
    }

    public void guardarArchivo() {
        metodosCursos.guardarArchivo();
    }
}
