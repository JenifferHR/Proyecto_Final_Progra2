package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author vaio01
 */
public class ArchivosMatricula {
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivosMatricula() {
   }

 public void crearArchivo(){
      try
      {
         archivoSalida=new ObjectOutputStream(new FileOutputStream("matricula.dat"));
         System.out.println("El archivo 'matricula', ha sido creado.");
      }
      catch(Exception e)
      {
          System.out.println("Error al crear el archivo: "+e);
      }
    }
    
    
    public void escribirInformacionEnElArchivo(Matricula matricula){
      try
      {
         archivoSalida.writeObject(matricula);
         System.out.println("Se escribió la información de forma correcta en: matricula");
      }
      catch(Exception e)
      {
          System.out.println("Error al escribir en el archivo: "+e);
      }
    }
    public String leerInformacion()
    {
     Matricula matricula=null;
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("matricula.dat"));
        matricula=(Matricula)archivoEntrada.readObject();
      }
      catch(Exception e)
      {
          System.out.println("Error al leer información del archivo: "+e);
      }
        return matricula.getInformacion();
    }
   
    public ArrayList<Matricula> leerInformacionCompleta()
    {
      ArrayList<Matricula>arrayMatricula=new ArrayList<Matricula>();
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("matricula.dat"));
        while(true)
        {
          arrayMatricula.add((Matricula)archivoEntrada.readObject());
        }
      }
      catch(Exception e)
      {
          System.out.println("Fin del archivo: "+e);
      }
        return arrayMatricula;
    }
    public boolean existeArchivo()
    {
      boolean existe=false;
      try{
        archivoEntrada=new ObjectInputStream(new FileInputStream("matricula.dat"));
        existe=true;
      }
      catch(Exception e)
      {
          System.out.println("No existe el archivo "+e);
      }
      return existe;
    }
}

