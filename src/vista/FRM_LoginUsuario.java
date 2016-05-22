/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controlador.ControladorLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author JenifferHR
 */
public class FRM_LoginUsuario extends javax.swing.JFrame {

    /**
     * Creates new form FRM_LoginUsuario
     */
    private  ControladorLogin controladorLogin;
    public FRM_LoginUsuario(FRM_VentanaPrincipal fRM_VentanaPrincipal) {
        initComponents();
        controladorLogin = new ControladorLogin(this,fRM_VentanaPrincipal);
        jBAceptar.addActionListener(controladorLogin);
        jBCancelar.addActionListener(controladorLogin);
        this.setSize(400, 420);
        setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jT_NombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jBAceptar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPassClave = new javax.swing.JPasswordField();
        jL_fondoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Nombre usuario");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(63, 249, 91, 18);

        jLabel2.setFont(new java.awt.Font("Oriya MN", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Contraseña");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(63, 294, 66, 18);
        getContentPane().add(jT_NombreUsuario);
        jT_NombreUsuario.setBounds(160, 250, 147, 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/23-128.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 70, 128, 128);

        jBAceptar.setText("Aceptar");
        jBAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBAceptarKeyPressed(evt);
            }
        });
        getContentPane().add(jBAceptar);
        jBAceptar.setBounds(60, 340, 90, 23);

        jBCancelar.setText("Cancelar");
        getContentPane().add(jBCancelar);
        jBCancelar.setBounds(188, 342, 100, 23);

        jPassClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPassClaveActionPerformed(evt);
            }
        });
        getContentPane().add(jPassClave);
        jPassClave.setBounds(160, 290, 147, 20);

        jL_fondoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondLogin.jpeg"))); // NOI18N
        getContentPane().add(jL_fondoLogin);
        jL_fondoLogin.setBounds(50, 180, 300, 190);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBAceptarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAceptarKeyPressed

    private void jPassClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPassClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPassClaveActionPerformed
public String getClave() {
        return jPassClave.getText();
    }

    public String getUsuario() {
        return jT_NombreUsuario.getText();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public boolean verificarCamposVacios()//verifica si los campos que debe llenar el usuario estan vacios
    {
        if(jT_NombreUsuario.getText().trim().equals("")||jPassClave.getText().trim().equals("")){
        
            return false;
        }
        return true; //si los campos estan llenos
    }
    
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JLabel jL_fondoLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPassClave;
    private javax.swing.JTextField jT_NombreUsuario;
    // End of variables declaration//GEN-END:variables
}
