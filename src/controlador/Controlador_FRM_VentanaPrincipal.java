/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import modelo.ArchivosEstudiantes;
import modelo.ArchivosUsuarios;
import modelo.Archivos_Usuario_XML;
import modelo.Conexion_BD;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_LoginUsuario;
import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_MantenimientoUsuario;
import vista.FRM_Matricula;
import vista.FRM_VentanaPrincipal;

/**
 *
 * @author tecnologiamultimedia
 */
public class Controlador_FRM_VentanaPrincipal implements ActionListener {

    FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes;
    FRM_MantenimientoCursos frm_MantenimientoCursos;
    FRM_Matricula frm_Matricula;
    FRM_MantenimientoUsuario fRM_MantenimientoUsuario;
    FRM_LoginUsuario fRM_LoginUsuario;
    FRM_VentanaPrincipal fRM_VentanaPrincipal;
    private Archivos_Usuario_XML archivos_Usuario_XML;
    ArchivosUsuarios archivosUsuarios;
    private Conexion_BD conexion_BD;

    public Controlador_FRM_VentanaPrincipal(FRM_VentanaPrincipal fRM_VentanaPrincipal) {

        this.fRM_VentanaPrincipal = fRM_VentanaPrincipal;
        frm_MantenimientoEstufiantes = new FRM_MantenimientoEstudiantes();
        frm_MantenimientoCursos = new FRM_MantenimientoCursos();
        abrirVentana();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salir")) {
            if (FRM_AlmacenarInformacion.numeroSeleccionado == 1)// Guarda en Archivo .dat
            {
                frm_MantenimientoEstufiantes.guardarArchivo();
                frm_MantenimientoCursos.guardarArchivo();
                frm_Matricula.guardarArchivo();
            }
            System.exit(0);
        }
        if (e.getActionCommand().equals("Estudiantes")) {
            frm_MantenimientoEstufiantes.setVisible(true);
        }
        if (e.getActionCommand().equals("Cursos")) {

            frm_MantenimientoCursos.setVisible(true);
        }
        if (e.getActionCommand().equals("Matricula")) {
            frm_Matricula = new FRM_Matricula(frm_MantenimientoEstufiantes, frm_MantenimientoCursos);
            frm_Matricula.setVisible(true);

        }
    }

    public void abrirVentana() {
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 1) {
            archivosUsuarios= new ArchivosUsuarios();
            if(archivosUsuarios.verificarArchivo())
            {
                fRM_LoginUsuario = new FRM_LoginUsuario(fRM_VentanaPrincipal);
                fRM_LoginUsuario.setVisible(true);
            }
            else
            {
                fRM_MantenimientoUsuario = new FRM_MantenimientoUsuario(fRM_VentanaPrincipal);
                fRM_MantenimientoUsuario.setVisible(true);
            }
        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 2) {
            conexion_BD = new Conexion_BD();

            if (conexion_BD.devolverNumeroUsuarios() > 0) {

                fRM_LoginUsuario = new FRM_LoginUsuario(fRM_VentanaPrincipal);
                fRM_LoginUsuario.setVisible(true);
            } else {
                fRM_MantenimientoUsuario = new FRM_MantenimientoUsuario(fRM_VentanaPrincipal);
                fRM_MantenimientoUsuario.setVisible(true);
            }

        }
        if (FRM_AlmacenarInformacion.numeroSeleccionado == 3) {
            archivos_Usuario_XML = new Archivos_Usuario_XML();
            if (archivos_Usuario_XML.devolverExistenciaUsuarios()) {

                fRM_LoginUsuario = new FRM_LoginUsuario(fRM_VentanaPrincipal);
                fRM_LoginUsuario.setVisible(true);

            } else {
                fRM_MantenimientoUsuario = new FRM_MantenimientoUsuario(fRM_VentanaPrincipal);
                fRM_MantenimientoUsuario.setVisible(true);
            }
        }
    }
}
