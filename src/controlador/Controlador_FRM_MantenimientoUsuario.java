/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ArchivosUsuarios;
import modelo.Archivos_Estudiante_XML;
import modelo.Archivos_Usuario_XML;
import modelo.Conexion_BD;
import modelo.MetodosUsuarios;
import modelo.Usuario;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_LoginUsuario;
//import modelo.MetodosUsuario;
import vista.FRM_MantenimientoUsuario;
import vista.FRM_VentanaPrincipal;

/**
 *
 * @author JenifferHR
 */
public class Controlador_FRM_MantenimientoUsuario implements ActionListener {

    //MetodosUsuario metodosUsuario;
    Conexion_BD conexion_BD;
    FRM_MantenimientoUsuario fRM_MantenimientoUsuario;
    FRM_VentanaPrincipal fRM_VentanaPrincipal;
    Archivos_Usuario_XML archivos_Usuario_XML;
    MetodosUsuarios metodosUsuariosDat;

    public Controlador_FRM_MantenimientoUsuario(FRM_MantenimientoUsuario fRM_MantenimientoUsuario, FRM_VentanaPrincipal fRM_VentanaPrincipal) {
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
            metodosUsuariosDat = new MetodosUsuarios();
        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) {
            conexion_BD = new Conexion_BD();
        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
            archivos_Usuario_XML = new Archivos_Usuario_XML();
        }
        this.fRM_MantenimientoUsuario = fRM_MantenimientoUsuario;

        this.fRM_VentanaPrincipal = fRM_VentanaPrincipal;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Consultar")) {

            if (!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")) {//verifica que el campo cedula tenga un dato
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    //consulta si existe el usuario
                    if (metodosUsuariosDat.consultarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                        fRM_MantenimientoUsuario.llenarCampos(metodosUsuariosDat.getArregloInformacion());
                        fRM_MantenimientoUsuario.habilitardicion();
                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("No Existe el Usuario");
                        fRM_MantenimientoUsuario.habilitarAgregar();
                    }
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a Bases de datos
                {
                    
                    if (conexion_BD.consultarUsuarioPorCedula(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0]) == true) {
                        fRM_MantenimientoUsuario.llenarCampos(conexion_BD.devolverInformacionUsuario());
                        fRM_MantenimientoUsuario.habilitardicion();
                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("No Existe el Usuario");
                        fRM_MantenimientoUsuario.habilitarAgregar();
                    }
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {
                    if (archivos_Usuario_XML.consultarInformacionDelXml(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                        fRM_MantenimientoUsuario.llenarCampos(archivos_Usuario_XML.getArregloInformacion());
                        fRM_MantenimientoUsuario.habilitardicion();
                    } else {
                        fRM_MantenimientoUsuario.habilitarAgregar();
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario No Registrado");
                    }
                }
            } else {
                fRM_MantenimientoUsuario.mostrarMensaje("Ingrese la cedula");
            }

        }
        if (e.getActionCommand().equalsIgnoreCase("Agregar")) {

            if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
            {
                if (!metodosUsuariosDat.consultarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                    if (fRM_MantenimientoUsuario.compararConstrasenias()) {
                        if (!metodosUsuariosDat.consultar(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2])) {
                            metodosUsuariosDat.agregarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                            metodosUsuariosDat.guardarArchivo();
                            fRM_MantenimientoUsuario.mostrarMensaje("Usuario Registrado Correctamente");
                            fRM_MantenimientoUsuario.resetearGUI();
                            fRM_MantenimientoUsuario.limpiarCampos();
                        } else {
                            fRM_MantenimientoUsuario.mostrarMensaje("El Nombre De Usuario ya existe");
                        }
                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("Las Contraseñas deben de ser igiales");
                    }
                } else {
                    fRM_MantenimientoUsuario.mostrarMensaje("La Cedula Digitada Ya Se encuentra Registrada");
                }
            }//fin del num 1
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
            {
                if (conexion_BD.consultarUsuarioPorCedula(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0]) == false) {
                    if (fRM_MantenimientoUsuario.compararConstrasenias()) {

                        conexion_BD.registrarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                        fRM_MantenimientoUsuario.mostrarMensaje("Se agregó el usuario de forma correcta");
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();

                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("Las contraseñas deben ser iguales");
                    }

                } else {
                    fRM_MantenimientoUsuario.mostrarMensaje("Ya existe el usuario");
                }

            }//fin del num 2
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
            {
                if (!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4].equals("")
                        && fRM_MantenimientoUsuario.compararConstrasenias()) {
                    Usuario usuario = new Usuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0],
                            fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1],
                            fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2],
                            fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3],
                            fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4]);
                    if (archivos_Usuario_XML.guardarEnXML(usuario)) {
                        fRM_MantenimientoUsuario.mostrarMensaje("Se Guardo El Usuario");
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();
                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("No se Pudo Guardar");
                    }
                } else {
                    fRM_MantenimientoUsuario.mostrarMensaje("Complete los datos y las contraseñas iguales");
                }

            }
        }

        if (e.getActionCommand()
                .equalsIgnoreCase("Modificar")) {
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
            {
                if ((!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4].equals("")
                        && fRM_MantenimientoUsuario.compararConstrasenias())) {
                    if (metodosUsuariosDat.consultarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                        metodosUsuariosDat.modificarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario Modificado");
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();

                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("No Existe el Usuario");
                    }
                }
            }
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
            {
                   
                    if ((!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4].equals("")
                        && fRM_MantenimientoUsuario.compararConstrasenias())) 
                    {
                        

                            if (conexion_BD.consultarUsuarioPorCedula(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0]))
                            {
                                conexion_BD.modificarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                                fRM_MantenimientoUsuario.mostrarMensaje("Usuario Modificado");
                                fRM_MantenimientoUsuario.resetearGUI();
                                fRM_MantenimientoUsuario.limpiarCampos();

                            }
                            else 
                            {
                            fRM_MantenimientoUsuario.mostrarMensaje("No Existe el Usuario");
                            }//fin del else

                }//fin del if que verifica los espacios
                    else
                    {
                        fRM_MantenimientoUsuario.mostrarMensaje("Debe completar los datos");
                    }
                    
            }//fin de bases de datos
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
            {
                if (!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4].equals("")
                        && fRM_MantenimientoUsuario.compararConstrasenias()) {

                    if (archivos_Usuario_XML.consultarInformacionDelXml(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                        archivos_Usuario_XML.modificarInformacionDelXml(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario Modificado");
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();
                    } else {
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario No Registrado");
                    }
                }// fin del if que verica campos
                else {
                    fRM_MantenimientoUsuario.mostrarMensaje("Ingrese los datos completos y contraseñas iguales");
                }
            }///fin del xml

        }
        if (e.getActionCommand().equalsIgnoreCase("Eliminar")) {

            if (!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")) {

                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
                    if (metodosUsuariosDat.consultarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) {
                        metodosUsuariosDat.eliminarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario Eliminado");
                    } // fin del if que verifica que exista
                    else {
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario No Registrado");
                    }// fin del else
                }// fin del if de .dat
                
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) { //bases de datos
                        
                    
                    if ((!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[1].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[2].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[3].equals("")
                        && !fRM_MantenimientoUsuario.devolviendoVectorInformacion()[4].equals("")
                        && fRM_MantenimientoUsuario.compararConstrasenias())) 
                    {
                        

                            if (conexion_BD.consultarUsuarioPorCedula(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0]))
                            {
                               conexion_BD.eliminarUsuario(fRM_MantenimientoUsuario.devolviendoVectorInformacion());
                                fRM_MantenimientoUsuario.mostrarMensaje("Usuario eliminado correctamente");
                                fRM_MantenimientoUsuario.resetearGUI();
                                fRM_MantenimientoUsuario.limpiarCampos();

                            }
                            else 
                            {
                            fRM_MantenimientoUsuario.mostrarMensaje("No Existe el Usuario");
                            }//fin del else

                }//fin del if que verifica los espacios
                    else
                    {
                        fRM_MantenimientoUsuario.mostrarMensaje("Debe completar los datos");
                    }
                    
                }//cierra bases de datos
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) 
                {
                    if(!fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0].equals(""))
                    {
                    if (archivos_Usuario_XML.consultarInformacionDelXml(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0])) 
                    {
                        archivos_Usuario_XML.eliminarInformacionDelXml(fRM_MantenimientoUsuario.devolviendoVectorInformacion()[0]);
                        fRM_MantenimientoUsuario.resetearGUI();
                        fRM_MantenimientoUsuario.limpiarCampos();
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario Eliminado");
                    } // fin del if que verifica que exista
                    else 
                    {
                        fRM_MantenimientoUsuario.mostrarMensaje("Usuario No Registrado");
                    }// fin del else
                }
                    else {
                    fRM_MantenimientoUsuario.mostrarMensaje("Ingrese la cedula");
                }// fin del else
                } // fin de if que verifica que sea xml
                
            }
        }

        if (e.getActionCommand()
                .equalsIgnoreCase("Ingresar")) {
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
                metodosUsuariosDat.guardarArchivo();
            }
            FRM_LoginUsuario fRM_LoginUsuario = new FRM_LoginUsuario(fRM_VentanaPrincipal);
            fRM_LoginUsuario.setVisible(true);
            fRM_MantenimientoUsuario.setVisible(false);
        }

    }

    public void verificarSeleccion(int numero) {

        if (numero == 3) {
            archivos_Usuario_XML = new Archivos_Usuario_XML();
        }
    }
}//fin de la clase
