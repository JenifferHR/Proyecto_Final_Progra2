/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador_FRM_MantenimientoUsuario;

/**
 *
 * @author JenifferHR
 */
public class Panel_InformacionLogin extends javax.swing.JPanel {

    String informacion[];
    
    public Panel_InformacionLogin() {
        initComponents();
        agregarItems();
        informacion = new String[5];
        //estadoInicial();
    }//fin del constructor

    public void agregarEventos(Controlador_FRM_MantenimientoUsuario controlador_FRM_MantenimientoUsuario) {

    }

    public void agregarItems() {
        jCB_Tipo.removeAllItems();
        jCB_Tipo.addItem("Administrador");
        jCB_Tipo.addItem("Usuario");

    }
    
    
    public String[] devolverVectorInformacion()
    {
        informacion[0] = jT_Cedula.getText();
        informacion[1] = jT_NombreCompleto.getText();
        informacion[2] = jT_NombreUsuario.getText();
        informacion[3] = jT_Contrasenia.getText();
        informacion[4] = jCB_Tipo.getSelectedItem().toString();
        
        return informacion;
        
    }
    
    public void llenarCampos(String arregloInformacionConsultada[])
    {
        jT_NombreCompleto.setText(arregloInformacionConsultada[1]);
        jT_NombreUsuario.setText(arregloInformacionConsultada[2]);
        jT_Contrasenia.setText(arregloInformacionConsultada[3]);
        jT_RepetirContrasenia.setText(arregloInformacionConsultada[3]);
        jCB_Tipo.setSelectedItem(arregloInformacionConsultada[4]);
        
    }//fin del metodo
    public void limpiar()
    {
        jT_NombreCompleto.setText("");
        jT_NombreUsuario.setText("");
        jT_Contrasenia.setText("");
        jT_RepetirContrasenia.setText("");
        jCB_Tipo.setSelectedItem("");
    }
    public void estadoInicial()
    {
        jT_Cedula.setEnabled(true);
        jT_Contrasenia.setEnabled(false);
        jT_RepetirContrasenia.setEnabled(false);
        jT_NombreCompleto.setEnabled(false);
        jT_NombreUsuario.setEnabled(false);
    }
    public void EstadoAgregar()
    {
        jT_Contrasenia.setEnabled(true);
        jT_RepetirContrasenia.setEnabled(true);
        jT_NombreCompleto.setEnabled(true);
        jT_NombreUsuario.setEnabled(true);
    }
    
    public boolean compararContrasenias()
    {
        if(jT_Contrasenia.getText().equalsIgnoreCase(jT_RepetirContrasenia.getText()))
        {
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCB_Tipo = new javax.swing.JComboBox();
        jT_Cedula = new javax.swing.JTextField();
        jT_NombreCompleto = new javax.swing.JTextField();
        jT_NombreUsuario = new javax.swing.JTextField();
        jT_Contrasenia = new javax.swing.JPasswordField();
        jT_RepetirContrasenia = new javax.swing.JPasswordField();

        jLabel1.setText("Cédula");

        jLabel2.setText("Nombre completo");

        jLabel3.setText("Nombre usuario");

        jLabel4.setText("Contraseña");

        jLabel5.setText("Repetir contraseña");

        jLabel6.setText("Tipo");

        jCB_Tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCB_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jT_Cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jT_NombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jT_NombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jT_Contrasenia)
                    .addComponent(jT_RepetirContrasenia))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jT_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jT_NombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jT_NombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jT_Contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jT_RepetirContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCB_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCB_Tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jT_Cedula;
    private javax.swing.JPasswordField jT_Contrasenia;
    private javax.swing.JTextField jT_NombreCompleto;
    private javax.swing.JTextField jT_NombreUsuario;
    private javax.swing.JPasswordField jT_RepetirContrasenia;
    // End of variables declaration//GEN-END:variables
}
