/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.FRM_Matricula;

/**
 *
 * @author JenifferHR
 */
public class Conexion_BD {

    Connection con;
    String InformacionEstudiante[];
    String InformacionCurso[];
    String InformacionMatricula[];
    String InformacionUsario[];
    ArrayList<String> informacionSiglas;
    boolean usuarios;
    String cedulaEncontrada;

    public Conexion_BD() {
        realizarConexion();

        InformacionCurso = new String[3];
        InformacionEstudiante = new String[2];
        InformacionMatricula = new String[1];
        InformacionUsario = new String[5];
        informacionSiglas = new ArrayList<>();

//        if(registrarEstudiante("10","Eugenio","chacarita"))
//        {
//            System.out.println("Registro correcto");
//        }  
//      
//     this.consultarEstudiante("1-151-601");
    }

    public void realizarConexion() {
        try {
            String userName = "root";
            String password = "";
            //String url = "jdbc:mysql://172.16.2.118:3306/matricula";     
            String url = "jdbc:mysql://localhost:3306/matricula";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, "123");
            System.out.println("Conexión Realizada");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }//fin del metodo realizar conexion

    public boolean registrarEstudiante(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO estudiante(cedula, nombre, direccion) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "')");
            return true;
            //execute es para instertar, update y delete
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }//fin del metodo registrar

    public boolean consultarEstudiante(String cedula) {
        boolean existencia = false;
        ResultSet rs = null;// objeto de tipo ResultSet
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM estudiante WHERE cedula=  '" + cedula + "'");//Solo lo guarda
            while (rs.next()) //mientras halla otro registro, lo toma y lo deja apuntando al siguiente
            {
                this.cedulaEncontrada = cedula;
                InformacionEstudiante[0] = rs.getString("nombre"); // obtiene el dato que se encuentra en la columna nombre
                InformacionEstudiante[1] = rs.getString("direccion");
                existencia = true;
                //int edad = rs.getInt(2);

            }
            rs.close();
        } catch (Exception e) {

            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existencia = false;
        }
        return existencia;

    }//fin del metodo consultar

    public String getCedulaEncontrada() {
        return this.cedulaEncontrada;
    }

    public boolean modificarEstudiante(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("UPDATE estudiante SET cedula='" + informacion[0] + "',nombre='" + informacion[1] + "', direccion='" + informacion[2] + "'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo modificar

    public boolean eliminarEstudiante(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("DELETE FROM estudiante WHERE cedula= '" + informacion[0] + "'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo eliminar

    public String[] devolverInformacionEstudiante() {
        return InformacionEstudiante;

    }//devuelve el vector temporal

    //inicio de los metodos curso
    public boolean registrarCurso(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO cursos(sigla, nombre, creditos, horario) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "','" + informacion[3] + "')");

            return true;
            //execute es para instertar, update y delete
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }//fin del metodo registrar

    public boolean consultarCurso(String sigla) {
        boolean existencia = false;
        ResultSet rs = null;
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM cursos WHERE sigla='" + sigla + "'");
            while (rs.next()) //mientras halla otro registro
            {

                InformacionCurso[0] = rs.getString("nombre");
                InformacionCurso[1] = rs.getString("creditos");
                InformacionCurso[2] = rs.getString("horario");

                existencia = true;
                //int edad = rs.getInt(2);

            }
            rs.close();
        } catch (Exception e) {

            System.out.println("SQLexception ejecutando sentencia: " + e.getMessage());
            existencia = false;
        }
        return existencia;

    }//fin del metodo consultar

    public boolean modificarCurso(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("UPDATE cursos SET sigla='" + informacion[0] + "', nombre='" + informacion[1] + "', creditos= '" + informacion[2] + "', horario= '" + informacion[3] + "'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo modificar

    public boolean eliminarCurso(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("DELETE FROM cursos WHERE sigla='" + informacion[0] + "'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo eliminar

    public String[] devolverInformacionCurso() {
        return InformacionCurso;

    }//devuelve el vector temporal

    //inicio de los metodos matricula
    public boolean registrarMatricula(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula WHERE codigo='" + informacion[0] + "'");
            if (rs.next()) {
                ejecuto = cmd.execute("INSERT INTO detalle_matricula (codigo, sigla) VALUES ('" + informacion[0] + "' , '" + informacion[2] + "')");
            } else {
                ejecuto = cmd.execute("INSERT INTO matricula(codigo, cedula) VALUES ('" + informacion[0] + "','" + informacion[1] + "')");
                ejecuto = cmd.execute("INSERT INTO detalle_matricula (codigo, sigla) VALUES ('" + informacion[0] + "' , '" + informacion[2] + "')");
            }

            rs.close();

            return true;
            //execute es para instertar, update y delete

        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }//fin del metodo registrar

    public boolean consultarMatricula(String codigo) {
        boolean existencia = false;
        ResultSet rs = null;
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula WHERE codigo='" + codigo + "'");
            System.err.println("entro rs");
            if (rs.next()) //mientras halla otro registro
            {
                String cedula = rs.getString("cedula");// optiene la cedula de la matricula de base de datos

                consultarEstudiante(cedula);// llama al metodo para que cargue el vector InformacionEstudiante
            System.err.println("consulta estudiante (ced)"+ consultarEstudiante(cedula) );

                rs = cmd.executeQuery("SELECT * FROM detalle_matricula WHERE codigo='" + codigo + "'");
                existencia = true;
                informacionSiglas=new ArrayList<>();
                while (rs.next()) {
                    informacionSiglas.add(rs.getString("sigla"));
                                System.err.println("entro detalle");

                }

                //int edad = rs.getInt(2);
            } else {
                System.err.println("no existen registros");
            }
            //rs.close();
        } catch (Exception e) {

            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        System.err.println("valor " + existencia);
        return existencia;

    }//fin del metodo consultar

    public boolean modificarMatricula(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("UPDATE matricula SET codigo='" + informacion[0] + "', cedula='" + informacion[1] + "'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo modificar

   
    public String[] devolverInformacionMatricula() {
        return InformacionMatricula;

    }//devuelve el vector temporal

    public ArrayList<String> getListaSiglas() {
        return informacionSiglas;
    }

    public void limpiarSiglas() {
        informacionSiglas = new ArrayList<>();
    }

    //inicio de los metodos usuario*****************************************************************************************
    public boolean registrarUsuario(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO usuario(cedula, nombreCompleto, nombreUsuario,contrasenia, tipo) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "','" + informacion[3] + "','" + informacion[4] + "')");

            return true;
            //execute es para instertar, update y delete
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }//fin del metodo registrar

    public boolean consultarUsuario(String nombreUsuario, String contrasenia) {
        boolean existencia = false;
        ResultSet rs = null;// objeto de tipo ResultSet
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            String sentencia = "SELECT * FROM usuario WHERE nombreUsuario='"
                    + nombreUsuario + "' AND contrasenia='" + contrasenia + "'";

            rs = cmd.executeQuery(sentencia);//Solo lo guarda
            while (rs.next()) //mientras halla otro registro, lo toma y lo deja apuntando al siguiente
            {

                InformacionUsario[0] = rs.getString("nombreCompleto");
                InformacionUsario[1] = rs.getString("nombreUsuario");
                InformacionUsario[2] = rs.getString("tipo");
                existencia = true;
                //int edad = rs.getInt(2);
            }
            rs.close();
        } catch (Exception e) {

            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
        return existencia;

    }//fin del metodo consultar
    
    public boolean consultarUsuarioPorCedula(String cedula)
    {
         boolean existencia = false;
        ResultSet rs = null;// objeto de tipo ResultSet
        Statement cmd = null;
        System.err.println("cedula solicitada "+cedula);
        try {
            cmd = con.createStatement();
            String sentencia = "SELECT * FROM usuario WHERE cedula='" + cedula + "'";

            rs = cmd.executeQuery(sentencia);//Solo lo guarda
            if(rs!=null)
            if (rs.next()) //mientras halla otro registro, lo toma y lo deja apuntando al siguiente
               {
                System.err.println("entro ---> NOMBRE COMPLETO -->"+rs.getString("nombreCompleto"));
                InformacionUsario[1] = rs.getString("nombreCompleto");
                   
                System.err.println("entro nOMBRE ----> "+rs.getString("nombreUsuario"));
                InformacionUsario[2] = rs.getString("nombreUsuario");
                   
                System.err.println("entro CLAVE ----> "+rs.getString("contrasenia"));
                InformacionUsario[3] = rs.getString("contrasenia");
                   
                System.err.println("entro TIPO---->"+rs.getString("tipo"));
                InformacionUsario[4] = rs.getString("tipo");
                   
                System.err.println("entro rs4");
                existencia = true;
                //int edad = rs.getInt(2);
            }
            rs.close();
        } catch (Exception e) {

            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
        return existencia;
    }

    public boolean modificarUsuario(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;
        String sentencia="UPDATE usuario Set cedula='" + informacion[0]+ "', nombreCompleto='" + informacion[1] + "', nombreUsuario= '" + informacion[2]+ "', contrasenia='" + informacion[3] + "', tipo='" + informacion[4] + "' WHERE cedula='" + informacion[0]+"'";
        System.err.println("sentencia == "+sentencia);
        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute(sentencia);
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo modificar

    public boolean eliminarUsuario(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecutivo;

        try {
            cmd = con.createStatement();
            ejecutivo = cmd.execute("Delete from usuario where cedula='" + informacion[0]+"'");
            return true;

        } catch (Exception e) {
            System.out.println("SQLeXCEPTION ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }//fin del metodo eliminar

    public int devolverNumeroUsuarios() {
        int contador = 0;
        ResultSet rs = null;// objeto de tipo ResultSet
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuario");//Solo lo guarda

            while (rs.next()) //mientras halla otro registro, lo toma y lo deja apuntando al siguiente
            {
                contador++; //va contando los usuarios
            }
            rs.close();
        } catch (Exception e) {

            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return contador;
    }

    public String[] devolverInformacionUsuario() {
        return InformacionUsario;

    }//devuelve el vector temporal

    public String devolverCodigo() {
        String codigoMatricula = "00001";
        int codigoEntero;
        System.err.println("hello");
        try {
                        System.err.println("hola entre primero");

            Statement statement = con.createStatement();
           
            ResultSet resultSet = statement.executeQuery("SELECT * FROM matricula");
            System.err.println("-------->");
            if (resultSet.last()) //Manda a la ultima fila de la tabla.
            {
                codigoEntero = Integer.parseInt(resultSet.getString(1)) + 1; //Toma el valor del codigo de la ultima fila.

                if (codigoEntero < 10) {
                    codigoMatricula = "0000" + codigoEntero;
                } else if (codigoEntero >= 10 && codigoEntero < 100) {
                    codigoMatricula = "000" + codigoEntero;
                } else if (codigoEntero >= 100 && codigoEntero < 1000) {
                    codigoMatricula = "00" + codigoEntero;
                } else if (codigoEntero >= 1000 && codigoEntero < 10000) {
                    codigoMatricula = "0" + codigoEntero;
                } else if (codigoEntero >= 10000) {
                    codigoMatricula = "" + codigoEntero;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en el metodo generarCodigo.\nError generado: " + ex);
        }
        return codigoMatricula;
    }

    public boolean devolverExistenciaUsuarios() {
        return usuarios;
    }

    //************************************************ Metodos para matricula************************************************
    // Este metodo elimina los detalles de la matriculas de uno en una 
    public boolean eliminarDetalleMatricula(String codigo, String sigla) {
        Statement cmd = null;
        boolean ejecuto = false;

        try {
            cmd = con.createStatement();
            cmd.executeUpdate("DELETE FROM detalle_matricula WHERE codigo='" + codigo + "'and sigla='" + sigla + "'");

            System.out.println("Detalle eliminado");
            ejecuto = true;
        } catch (Exception e) {
            System.out.println("SQLException en metodo eliminar detalle de matricula ejecutando sentencia: " + e.getMessage());
        }
        return ejecuto;
    }

}
