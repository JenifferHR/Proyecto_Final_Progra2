/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FRM_AlmacenarInformacion;
import vista.FRM_VentanaPrincipal;

/**
 *
 * @author JenifferHR
 */
public class Controlador_FRM_AlmacenarInformacion implements ActionListener {

    private FRM_AlmacenarInformacion fRM_AlmacenarInformacion;
    int sumatoria=0;
    private Controlador_FRM_VentanaPrincipal controlador_FRM_VentanaPrincipal;
    public Controlador_FRM_AlmacenarInformacion(FRM_AlmacenarInformacion fRM_AlmacenarInformacion) {
        this.fRM_AlmacenarInformacion = fRM_AlmacenarInformacion;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Ingresar")) {
            if(fRM_AlmacenarInformacion.seleccionarJradioButton()>0)
            {
                
                FRM_VentanaPrincipal fRM_VentanaPrincipal = new FRM_VentanaPrincipal();
                if(sumatoria==1)
                {
                    fRM_AlmacenarInformacion.setVisible(false);
                }
                sumatoria++;
                fRM_AlmacenarInformacion.numeroSeleccionado=fRM_AlmacenarInformacion.seleccionarJradioButton();
            }
                
            else
            {
                //muestra un mensaje, 1 = al tipo de mensaje ERROR
                fRM_AlmacenarInformacion.mostrarMensaje("Debe seleccionar una opción",1);
            }
           
        }

    }//fin del metodo
}//fin de la clase