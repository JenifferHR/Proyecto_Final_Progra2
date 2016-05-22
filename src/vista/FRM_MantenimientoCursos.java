package vista;

import controlador.Controlador_FRM_MantenimientoCursos;
import javax.swing.JOptionPane;
/**
 *
 * @author tecnologiamultimedia
 */
public class FRM_MantenimientoCursos extends javax.swing.JFrame {

    public Controlador_FRM_MantenimientoCursos controlador;

    public FRM_MantenimientoCursos() {
        initComponents();
        controlador = new Controlador_FRM_MantenimientoCursos(this);
        agregarEventos();
        resetearGUI();
    }
    public void guardarArchivo()
    {
       controlador.guardarArchivo();
    }
    public void agregarEventos() {
        this.panel_Botones1.agregarEventosCursos(controlador);
        this.panel_Cursos1.agregarEventos(controlador);
    }

    public String devolverSigla() {
        return this.panel_Cursos1.devolverSigla();
    }

    public void habilitarEdicion() {
        this.panel_Botones1.habilitarEdicion();
        this.panel_Cursos1.habilitarEdicion();
    }

    public void mostrarInformacion(String arreglo[]) {
        this.panel_Cursos1.mostrarInformacion(arreglo);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void habilitarAgregar() {
        this.panel_Botones1.habilitarAgregar();
        this.panel_Cursos1.habilitarCampos();
    }

    public String[] devolverInformacion() {
        return this.panel_Cursos1.devolverInformacion();
    }

    public void resetearGUI() {
        this.panel_Botones1.deshabilitarBotones();
        this.panel_Cursos1.deshabilitarCampos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Botones1 = new vista.Panel_Botones();
        panel_Cursos1 = new vista.Panel_Cursos();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_Cursos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_Cursos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.Panel_Botones panel_Botones1;
    private vista.Panel_Cursos panel_Cursos1;
    // End of variables declaration//GEN-END:variables
}
