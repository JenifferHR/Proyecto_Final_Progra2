/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Archivos_Usuario_XML;
import modelo.Conexion_BD;
import modelo.MetodosUsuarios;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_LoginUsuario;
import vista.FRM_VentanaPrincipal;

/**
 *
 * @author JenifferHR
 */
public class ControladorLogin implements ActionListener {

    private FRM_LoginUsuario fRM_LoginUsuario;
    private FRM_VentanaPrincipal ventanaPrincipal;
    private Archivos_Usuario_XML archivos_Usuario_XML;
    private Conexion_BD conexion_BD;
private MetodosUsuarios metodosUsuarios;
    public ControladorLogin(FRM_LoginUsuario fRM_LoginUsuario, FRM_VentanaPrincipal ventanaPrincipal) {
        this.fRM_LoginUsuario = fRM_LoginUsuario;
        this.ventanaPrincipal = ventanaPrincipal;
        if(FRM_AlmacenarInformacion.numeroSeleccionado==1)
        {
         metodosUsuarios= new MetodosUsuarios();
        }
        if(FRM_AlmacenarInformacion.numeroSeleccionado==2)
        {
        conexion_BD = new Conexion_BD();
        }
          if(FRM_AlmacenarInformacion.numeroSeleccionado==3)
        {
        archivos_Usuario_XML = new Archivos_Usuario_XML();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Aceptar")) {

            if (!fRM_LoginUsuario.getClave().equals("") && !fRM_LoginUsuario.getUsuario().equals("")) {
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// se agrega a Archivo .dat
                {
                    if(metodosUsuarios.verificarDatosUsuario(fRM_LoginUsuario.getClave(), fRM_LoginUsuario.getUsuario()))
                    {
                         ventanaPrincipal.setVisible(true);
                        fRM_LoginUsuario.setVisible(false);
                    }
                    else
                    {
                          fRM_LoginUsuario.mostrarMensaje("Usuario Incorrecto"); 
                    }
                }//fin del num 1
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 2)// se agrega a bases de datos
                {
                    if(conexion_BD.consultarUsuario(fRM_LoginUsuario.getUsuario(),fRM_LoginUsuario.getClave()))
                    {
                        ventanaPrincipal.setVisible(true);
                        fRM_LoginUsuario.setVisible(false);
                    }
                    else
                    {
                        fRM_LoginUsuario.mostrarMensaje("Usuario Incorrecto");
                    }
                }
                if (FRM_AlmacenarInformacion.numeroSeleccionado == 3)// se agrega a XML
                {

                    if (archivos_Usuario_XML.consultarInformacionDelUsuario(fRM_LoginUsuario.getUsuario(), fRM_LoginUsuario.getClave())) {
                        ventanaPrincipal.setVisible(true);
                        fRM_LoginUsuario.setVisible(false);
                    } else {
                        fRM_LoginUsuario.mostrarMensaje("Usuario Incorrecto");
                    }
                }
            }
            
            else {
                fRM_LoginUsuario.mostrarMensaje("Complete los datos");
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {
            System.exit(0);
        }
    }

}
