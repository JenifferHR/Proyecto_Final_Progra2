/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador_FRM_MantenimientoUsuario;
import javax.swing.JOptionPane;

/**
 *
 * @author JenifferHR
 */
public class FRM_MantenimientoUsuario extends javax.swing.JFrame {

    public Controlador_FRM_MantenimientoUsuario controlador_FRM_MantenimientoUsuario;
    int numero;
    
    public FRM_MantenimientoUsuario(FRM_VentanaPrincipal fRM_VentanaPrincipal) {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        controlador_FRM_MantenimientoUsuario = new Controlador_FRM_MantenimientoUsuario(this,fRM_VentanaPrincipal);
        panel_Botones1.habilitarAgregar();
        panel_InformacionLogin1.EstadoAgregar();
        panel_Botones1.habilitarEdicion();
        panel_Botones1.agregarEventosUsuario(controlador_FRM_MantenimientoUsuario);
        jBIngresar.addActionListener(controlador_FRM_MantenimientoUsuario);
        this.numero = numero;
        controlador_FRM_MantenimientoUsuario.verificarSeleccion(numero);
    }
    
   
    
    

    public String[] devolviendoVectorInformacion()
    {
        return panel_InformacionLogin1.devolverVectorInformacion();
    }
    public void llenarCampos(String arregloInformacionConsultada[])
    {
        this.panel_InformacionLogin1.llenarCampos(arregloInformacionConsultada);
    }
    public void mostrarMensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(null, mensaje);
    }
     public void resetearGUI()
    {
        this.panel_Botones1.deshabilitarBotones();
        //this.panel_InformacionLogin1.deshabilitarCampos();
    }
    
   public void limpiarCampos()
   {
       this.panel_InformacionLogin1.limpiar();
   }
   public  void habilitarAgregar()
   {
       this.panel_Botones1.habilitarAgregar();
   }
   public  void habilitardicion()
   {
       this.panel_Botones1.habilitarEdicion();
   }
    public boolean compararConstrasenias()
    {
        return panel_InformacionLogin1.compararContrasenias();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_InformacionLogin1 = new vista.Panel_InformacionLogin();
        panel_Botones1 = new vista.Panel_Botones();
        jBIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jBIngresar.setText("Ingresar");

        jLabel1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLabel1.setText("Datos de nuevo usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBIngresar))
                            .addComponent(panel_InformacionLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel1)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_InformacionLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jBIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBIngresar;
    private javax.swing.JLabel jLabel1;
    private vista.Panel_Botones panel_Botones1;
    private vista.Panel_InformacionLogin panel_InformacionLogin1;
    // End of variables declaration//GEN-END:variables
}
