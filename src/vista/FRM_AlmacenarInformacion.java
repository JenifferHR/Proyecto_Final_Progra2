/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador_FRM_AlmacenarInformacion;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author JenifferHR
 */
public class FRM_AlmacenarInformacion extends javax.swing.JFrame {

    Controlador_FRM_AlmacenarInformacion controlador;

    /**
     * Creates new form FRM_AlmacenarInformacion
     */
    public static int numeroSeleccionado=0;
    public FRM_AlmacenarInformacion() {
        initComponents();
        //se crean los JRadioButton

        controlador = new Controlador_FRM_AlmacenarInformacion(this);
        agruparRadioButton();//metodo que contiene el buttonGroup
        agregarControlador();

        jButton1.addActionListener(controlador);
    }

    //este metodo es para agrupar los radioButton con el button group
    public void agruparRadioButton() {
        buttonGroup1.add(jR_ArchivosPlanos);
        buttonGroup1.add(jR_BasesDatos);
        buttonGroup1.add(jR_Xml);
    }

    public void agregarControlador() {
        jR_ArchivosPlanos.addActionListener(controlador);
        jR_BasesDatos.addActionListener(controlador);
        jR_Xml.addActionListener(controlador);

    }

    public int seleccionarJradioButton() {

        if (jR_ArchivosPlanos.isSelected()) {

            return 1; //selecciono archivosPlanos
        }
        if (jR_BasesDatos.isSelected()) {

            return 2;// selecciono bases de datos
        }
        if (jR_Xml.isSelected()) {

            return 3;// selecciono xml
        }

        return 0; // no selecciono nada
    }

    public void mostrarMensaje(String mensaje, int tipo) {
        if (tipo == 1) {
            JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        if (tipo == 2) {
            JOptionPane.showMessageDialog(this, mensaje, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        }
        if (tipo == 3) {
            JOptionPane.showMessageDialog(this, mensaje, "ALERTA", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jR_ArchivosPlanos = new javax.swing.JRadioButton();
        jR_BasesDatos = new javax.swing.JRadioButton();
        jR_Xml = new javax.swing.JRadioButton();
        jL_Titulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoInformacion.jpg"))); // NOI18N

        buttonGroup1.add(jR_ArchivosPlanos);
        jR_ArchivosPlanos.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jR_ArchivosPlanos.setText("Archivos planos (.dat)");
        jR_ArchivosPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ArchivosPlanosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jR_BasesDatos);
        jR_BasesDatos.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jR_BasesDatos.setText("Bases de datos");

        buttonGroup1.add(jR_Xml);
        jR_Xml.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jR_Xml.setText("XML");

        jL_Titulo.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jL_Titulo.setForeground(new java.awt.Color(255, 102, 153));
        jL_Titulo.setText("Seleccione la fuente de información");

        jButton1.setText("Ingresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jL_Titulo)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jR_ArchivosPlanos)
                                .addComponent(jR_BasesDatos)
                                .addComponent(jR_Xml)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jL_Titulo)
                .addGap(50, 50, 50)
                .addComponent(jR_ArchivosPlanos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jR_BasesDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jR_Xml)
                .addGap(13, 13, 13)
                .addComponent(jButton1)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jR_ArchivosPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_ArchivosPlanosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jR_ArchivosPlanosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FRM_AlmacenarInformacion almacenarInformacion = new FRM_AlmacenarInformacion();
        almacenarInformacion.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jL_Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jR_ArchivosPlanos;
    private javax.swing.JRadioButton jR_BasesDatos;
    private javax.swing.JRadioButton jR_Xml;
    // End of variables declaration//GEN-END:variables
}
