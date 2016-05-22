package vista;

import controlador.Controlador_FRM_MantenimientoEstudiantes;
import javax.swing.JOptionPane;
/**
 *
 * @author tecnologiamultimedia
 */
public class FRM_MantenimientoEstudiantes extends javax.swing.JFrame {

   public Controlador_FRM_MantenimientoEstudiantes controlador_FRM_MantenimientoEstudiantes;
    
    public FRM_MantenimientoEstudiantes() {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        controlador_FRM_MantenimientoEstudiantes = new Controlador_FRM_MantenimientoEstudiantes(this);
        this.panel_Botones1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        this.panel_InformacionBasica1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        resetearGUI();
    }
    public String[] devolverInformacion()
    {
        return this.panel_InformacionBasica1.devolverInformacion();
    }
    public String devolverCedula()
    {
        return this.panel_InformacionBasica1.devolverCedula();
    }
    public void mostrarInformacion(String arreglo[])
    {
        this.panel_InformacionBasica1.mostrarInformacion(arreglo);
    }
    public void guardarArchivo()
    {
       controlador_FRM_MantenimientoEstudiantes.guardarArchivo();
    }
    public void mostrarMensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public void habilitarAgregar()
    {
        this.panel_Botones1.habilitarAgregar();
        this.panel_InformacionBasica1.habilitarCampos();
    }
    public void resetearGUI()
    {
        this.panel_Botones1.deshabilitarBotones();
        this.panel_InformacionBasica1.deshabilitarCampos();
    }
    public void habilitarEdicion()
    {
        this.panel_Botones1.habilitarEdicion();
        this.panel_InformacionBasica1.habilitarEdicion();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Botones1 = new vista.Panel_Botones();
        panel_InformacionBasica1 = new vista.Panel_InformacionBasica();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_InformacionBasica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_InformacionBasica1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.Panel_Botones panel_Botones1;
    private vista.Panel_InformacionBasica panel_InformacionBasica1;
    // End of variables declaration//GEN-END:variables
}
