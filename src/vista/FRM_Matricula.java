package vista;

import controlador.Controlador_FRM_Matricula;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ArchivosMatricula;

/**
 *
 * @author tecnologiamultimedia
 */
public class FRM_Matricula extends javax.swing.JFrame {

    DefaultTableModel modelo;
    Controlador_FRM_Matricula controlador;

    public FRM_Matricula(FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes, FRM_MantenimientoCursos frm_MantenimientoCursos) {
        initComponents();
        setLocation(200, 200);
        controlador = new Controlador_FRM_Matricula(frm_MantenimientoCursos, frm_MantenimientoEstufiantes, this);
        agregarEventos();
        //metodosMatricula = controlador.metodosMatricula;
        modelo = new DefaultTableModel();
        colocarTitulosTabla();
        deshabilitarInicio();
        colocarCodigo(controlador.devolverCod());
        limpiarTabla();
        colocarCodigo(controlador.codigo());
    }

    public void agregarInformacionTabla(String arreglo[]) {
        modelo.addRow(arreglo);
        colocarNombreEstudiante(arreglo[2]);
        this.jt_Cedula.setText(arreglo[1]);
    }

    public void agregarInformacionTabla() {
        String arreglo[] = new String[4];
        arreglo[0] = this.jt_CodigoMatricula.getText();
        arreglo[1] = this.jt_Cedula.getText();
        arreglo[2] = this.jt_NombreEstudiante.getText();
        arreglo[3] = this.jt_Sigla.getText();
        modelo.addRow(arreglo);
    }

    public void agregarEventos() {
        this.btn_ConsultaRapidaCedula.addActionListener(controlador);
        this.btn_ConsultaRapidaSigla.addActionListener(controlador);
        this.btn_Finalizar.addActionListener(controlador);
        this.panel_Botones1.agregarEventos(controlador);
    }

    public void cargarInformacionTabla(ArrayList<String> siglas, String nombre, String codigo, String cedula) {

        for (int i = 0; i < siglas.size(); i++) {
            String arreglo[] = new String[4];
            arreglo[0] = codigo;
            arreglo[1] = cedula;
            arreglo[2] = nombre;
            arreglo[3] = siglas.get(i);
            modelo.addRow(arreglo);
        }
    }

    public void resetearVentana() {
        this.jt_Cedula.setText("");
        this.jt_NombreEstudiante.setText("");
        this.jt_Sigla.setText("");
        this.jt_NombreCurso.setText("");
        int tamanio = modelo.getRowCount();

        for (int contador = 0; contador < modelo.getRowCount(); contador++) {
            modelo.removeRow(0);
        }
    }

    public void limpiarSigla() {
        this.jt_Sigla.setText("");
        this.jt_NombreCurso.setText("");
    }

    public String devolverCedula() {
        return this.jt_Cedula.getText();
    }

    public String devolverSigla() {
        return this.jt_Sigla.getText();
    }

    //este metodo sera utilizado unicamente cuando el usuario desee eliminar una matricula guardada en XML
    //Ya que no se elimina un detalle de matricula como en el caso de bases de datos
    public String opcionSeleccionada()
    {
        String opcion = JOptionPane.showInputDialog(null, "No se permite eliminar ninguna matricula, elija una opcion:\n 1)Eliminar definitivamente \n 2)Cancelar");
        return opcion;
    }

    public int devolverFilaSeleccionada() {
        return this.tbl_Tabla.getSelectedRow();
    }

    public String devoverDatosSeleccionados() {
        /*
         Devuelve la sigla de la fila que se selecciono
         */
        int numero = tbl_Tabla.getSelectedRow();
        if (numero >= 0) {
            return "" + modelo.getValueAt(numero, 3);
        }
        return null;
    }

    public String devolverCodigo() {
        return this.jt_CodigoMatricula.getText();
    }

    public void colocarNombreEstudiante(String nombre) {
        this.jt_NombreEstudiante.setText(nombre);
    }

    public String devolverDato(int fila, int columna) {
        return "" + modelo.getValueAt(fila, columna);
    }

    public void colocarNombreCurso(String nombre) {
        this.jt_NombreCurso.setText(nombre);
    }

    public void colocarCedula(String cedula) {
        this.jt_Cedula.setText(cedula);
    }

    public void colocarSigla(String sigla) {
        this.jt_Sigla.setText(sigla);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void colocarTitulosTabla() {
        this.tbl_Tabla.setModel(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre Est.");
        modelo.addColumn("Sigla");
    }

    public void guardarArchivo() {
        controlador.guardarArchivo();
    }

    public void habilitarAgregar() {
        this.panel_Botones1.habilitarAgregar();
    }

    public void habilitandoModificacion() {
        this.panel_Botones1.habilitarBtnModificar();
    }

    public void colocarCodigo(String colocarCod) {
        this.jt_CodigoMatricula.setText(colocarCod);
    }

    public int getCantidadFilas() {
        return modelo.getRowCount();
    }

    public void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }

    public void estadoJTCurso() {
        this.jt_Cedula.setEnabled(true);
        this.jt_Sigla.setEnabled(true);
    }

    public void habilitarEdicion() {
        this.panel_Botones1.habilitarEdicion();
    }

    public void deshabilitarInicio() {
        this.panel_Botones1.deshabilitarInicio();
    }

    public String devolverNombreCurso() {
        return this.jt_NombreCurso.getText();
    }

    public String devolverNombreEstudiante() {
        return this.jt_NombreEstudiante.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jt_Cedula = new javax.swing.JTextField();
        btn_ConsultaRapidaCedula = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jt_NombreEstudiante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jt_Sigla = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jt_NombreCurso = new javax.swing.JTextField();
        btn_ConsultaRapidaSigla = new javax.swing.JButton();
        jl_codigoMatricula = new javax.swing.JLabel();
        jt_CodigoMatricula = new javax.swing.JTextField();
        btn_Finalizar = new javax.swing.JButton();
        panel_Botones1 = new vista.Panel_Botones();

        tbl_Tabla.setForeground(new java.awt.Color(0, 153, 153));
        tbl_Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tbl_Tabla);

        jLabel1.setText("Cédula");

        btn_ConsultaRapidaCedula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btn_ConsultaRapidaCedula.setActionCommand("ConsultaRapidaCedula");

        jLabel2.setText("Nombre Estudiante");

        jt_NombreEstudiante.setEnabled(false);

        jLabel3.setText("Sigla");

        jLabel4.setText("Nombre Curso");

        jt_NombreCurso.setEnabled(false);

        btn_ConsultaRapidaSigla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btn_ConsultaRapidaSigla.setActionCommand("ConsultaRapidaSigla");

        jl_codigoMatricula.setText("Código Matrícula");

        btn_Finalizar.setText("Finalizar Matrícula");
        btn_Finalizar.setActionCommand("Finalizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jt_Cedula)
                                    .addComponent(jt_NombreEstudiante)
                                    .addComponent(jt_Sigla)
                                    .addComponent(jt_NombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_ConsultaRapidaCedula)
                                    .addComponent(btn_ConsultaRapidaSigla)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_codigoMatricula)
                                .addGap(37, 37, 37)
                                .addComponent(jt_CodigoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Finalizar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_codigoMatricula)
                    .addComponent(jt_CodigoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jt_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ConsultaRapidaCedula))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jt_NombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jt_Sigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ConsultaRapidaSigla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jt_NombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(btn_Finalizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ConsultaRapidaCedula;
    private javax.swing.JButton btn_ConsultaRapidaSigla;
    private javax.swing.JButton btn_Finalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jl_codigoMatricula;
    private javax.swing.JTextField jt_Cedula;
    private javax.swing.JTextField jt_CodigoMatricula;
    private javax.swing.JTextField jt_NombreCurso;
    private javax.swing.JTextField jt_NombreEstudiante;
    private javax.swing.JTextField jt_Sigla;
    private vista.Panel_Botones panel_Botones1;
    private javax.swing.JTable tbl_Tabla;
    // End of variables declaration//GEN-END:variables

}
