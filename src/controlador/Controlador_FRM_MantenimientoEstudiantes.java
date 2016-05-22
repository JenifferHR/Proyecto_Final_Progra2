package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Conexion_BD;
import modelo.MetodosEstudiantes;
import modelo.Metodos_Estudiante_XML;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_MantenimientoEstudiantes;
/**
 *
 * @author jeniffer
 */
public class Controlador_FRM_MantenimientoEstudiantes implements ActionListener {

    public Conexion_BD conexion_BD;
    public MetodosEstudiantes metodosEstudiantes;
    FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes;
    private Metodos_Estudiante_XML metodos_Estudiante_XML;

    public Controlador_FRM_MantenimientoEstudiantes(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes) {
        this.frm_MantenimientoEstudiantes = frm_MantenimientoEstudiantes;
        metodosEstudiantes= new MetodosEstudiantes();
        metodos_Estudiante_XML = new Metodos_Estudiante_XML(); // Crea la clase metodos
        conexion_BD = new Conexion_BD();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Agregar")) {

            if (verificarEspacios()) {

                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    metodosEstudiantes.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                    metodosEstudiantes.guardarArchivo();
                    frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                    frm_MantenimientoEstudiantes.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) // se agrega a Bases de datos
                {
                    conexion_BD.registrarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                    frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                    frm_MantenimientoEstudiantes.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)//// se agrega a XML
                {
                    metodos_Estudiante_XML.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                    frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                    frm_MantenimientoEstudiantes.resetearGUI();
                }
            }// fin del metodo verificar espacios
        }
        if (e.getActionCommand().equals("Consultar") || e.getActionCommand().equals("Consulta_Rapida")) {

           if (!frm_MantenimientoEstudiantes.devolverCedula().trim().equals("")) {
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                    {
                        buscar();
                    }
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Bases de datos
                    {
                        buscarEstudiante();
                    }
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                    {
                        if (metodos_Estudiante_XML.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
                            frm_MantenimientoEstudiantes.mostrarInformacion(metodos_Estudiante_XML.getArregloInformacion());
                            frm_MantenimientoEstudiantes.habilitarEdicion();
                        } else {
                            frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante no existe");
                            frm_MantenimientoEstudiantes.habilitarAgregar();
                        }
                    }
                } else {
                    frm_MantenimientoEstudiantes.mostrarMensaje("Debe digitar una cedula");
                }
        }
        if (e.getActionCommand().equals("Modificar")) {

            if (verificarEspacios()) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    metodosEstudiantes.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                    frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                    frm_MantenimientoEstudiantes.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                {
                    conexion_BD.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                    frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                    frm_MantenimientoEstudiantes.resetearGUI();
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    if (metodos_Estudiante_XML.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
                        metodos_Estudiante_XML.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                        frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                        frm_MantenimientoEstudiantes.resetearGUI();
                    } else {
                        frm_MantenimientoEstudiantes.mostrarMensaje("La cedula no se encontro");
                    }
                }
            }//fin de verificar espacios
         }
        if (e.getActionCommand().equals("Eliminar")) {

            if (verificarEspacios()) {
                if (!frm_MantenimientoEstudiantes.devolverCedula().trim().equals("")) {
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                    {
                        metodosEstudiantes.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                        frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                        frm_MantenimientoEstudiantes.resetearGUI();
                    }
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Archivo Bases de datos
                    {
                        conexion_BD.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                        frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                        frm_MantenimientoEstudiantes.resetearGUI();
                    }
                    if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a Archivo XML
                    {
                        if (metodos_Estudiante_XML.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
                            metodos_Estudiante_XML.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                            frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");

                            frm_MantenimientoEstudiantes.resetearGUI();
                        } else {
                            frm_MantenimientoEstudiantes.mostrarMensaje("La cedula no se encontro");
                        }
                    }
                } else {
                    frm_MantenimientoEstudiantes.mostrarMensaje("Debe digitar una cedula");
                }
            }
        }
    }//fin del actionPerformed

    //Metodo que es utilizado para consultar en la base de datos
      public void buscarEstudiante() {
        if (conexion_BD.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
            frm_MantenimientoEstudiantes.mostrarInformacion(conexion_BD.devolverInformacionEstudiante());
            frm_MantenimientoEstudiantes.habilitarEdicion();
        } else {
            frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");
            frm_MantenimientoEstudiantes.habilitarAgregar();
        }
    }
    public boolean verificarEspacios() {
        if (!frm_MantenimientoEstudiantes.devolverInformacion()[0].trim().equals("")
                && !frm_MantenimientoEstudiantes.devolverInformacion()[1].trim().equals("")
                && !frm_MantenimientoEstudiantes.devolverInformacion()[0].trim().equals("")) {
            return true;
        } else {
            frm_MantenimientoEstudiantes.mostrarMensaje("Complete los datos");
            return false;
        }
    }
    public void buscar() //Metodo que es utilizado para consultar en Archivos planos.
    {
        if(metodosEstudiantes.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula()))
            {
                frm_MantenimientoEstudiantes.mostrarInformacion(metodosEstudiantes.getArregloInformacion());
                frm_MantenimientoEstudiantes.habilitarEdicion();
            }
            else
            {
                frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");
                frm_MantenimientoEstudiantes.habilitarAgregar();
            }
    }
   public void guardarArchivo()
   {
     metodosEstudiantes.guardarArchivo();
   }
}//fin de la clase
