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
public class ArchivosEstudiantes {
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivosEstudiantes() {
   }

     public void crearArchivo(){
      try
      {
         archivoSalida=new ObjectOutputStream(new FileOutputStream("estudiante.dat"));
         System.out.println("El archivo 'estudiante.dat', ha sido creado.");
      }
      catch(Exception e)
      {
          System.out.println("Error al crear el archivo: "+e);
      }
    }
    
    public void escribirInformacionEnElArchivo(Estudiante estudiante){
      try
      {
         archivoSalida.writeObject(estudiante);
         System.out.println("Se escribió la información de forma correcta en: estudiante");
      }
      catch(Exception e)
      {
          System.out.println("Error al escribir en el archivo: "+e);
      }
    }
    public String leerInformacion()
    {
     Estudiante estudiante=null;
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("estudiante.dat"));
        estudiante=(Estudiante)archivoEntrada.readObject();
      }
      catch(Exception e)
      {
          System.out.println("Error al leer información del archivo: "+e);
      }
        return estudiante.getInformacion();
    }
   
    public ArrayList<Estudiante> leerInformacionCompleta()
    {
      ArrayList<Estudiante>arrayEstudiantes=new ArrayList<Estudiante>();
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("estudiante.dat"));
        while(true)
        {
          arrayEstudiantes.add((Estudiante)archivoEntrada.readObject()); //Casting
        }
      }
      catch(Exception e)
      {
          System.out.println("Fin del archivo: "+e);
      }
        return arrayEstudiantes;
    }
    public boolean existeArchivo()
    {
      boolean existe=false;
      try{
        archivoEntrada=new ObjectInputStream(new FileInputStream("estudiante.dat"));
        existe=true;
      }
      catch(Exception e)
      {
          System.out.println("No existe el archivo "+e);
      }
      return existe;
    }
    
}//Fin de la clase ArchivosEstudiantes
