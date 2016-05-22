package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author vaio01
 */
public class ArchivosUsuarios {
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivosUsuarios() {
   }

     public void crearArchivo(){
      try
      {
         archivoSalida=new ObjectOutputStream(new FileOutputStream("usuario.dat"));
         System.out.println("El archivo 'usuario.dat', ha sido creado.");
      }
      catch(Exception e)
      {
          System.out.println("Error al crear el archivo: "+e);
      }
    }
    
    public void escribirInformacionEnElArchivo(Usuario usuario){
      try
      {
          System.err.println("entro    pppppp "+usuario.getInformation());
         archivoSalida.writeObject(usuario);
         System.out.println("Se escribió la información de forma correcta en: usuario");
      }
      catch(Exception e)
      {
          System.out.println("Error al escribir en el archivo: "+e);
      }
    }
    public String leerInformacion()
    {
     Usuario usuario=null;
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("usuario.dat"));
        usuario=(Usuario)archivoEntrada.readObject();
      }
      catch(Exception e)
      {
          System.out.println("Error al leer información del archivo: "+e);
      }
        return usuario.getInformation();
    }
   
    public ArrayList<Usuario> leerInformacionCompleta()
    {
      ArrayList<Usuario>arrayUsuario=new ArrayList<Usuario>();
      try
      {
        archivoEntrada=new ObjectInputStream(new FileInputStream("usuario.dat"));
        while(true)
        {
          arrayUsuario.add((Usuario)archivoEntrada.readObject()); //Casting
        }
      }
      catch(Exception e)
      {
          System.out.println("Fin del archivo: "+e);
      }
        return arrayUsuario;
    }
    public boolean existeArchivo()
    {
      boolean existe=false;
      try{
        archivoEntrada=new ObjectInputStream(new FileInputStream("usuario.dat"));
        existe=true;
      }
      catch(Exception e)
      {
          System.out.println("No existe el archivo "+e);
      }
      return existe;
    }
    public Boolean verificarArchivo()
    {
        File file = new File("usuario.dat");
        if(file.exists())
        {
            return  true;
        }
        return false;
    }
}
