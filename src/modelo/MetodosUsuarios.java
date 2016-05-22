package modelo;

import java.util.ArrayList;

/**
 *
 * @author vaio01
 */
public class MetodosUsuarios {
   
    private ArrayList <Usuario> arrayUsuario;
    String arregloInformacionConsultada[]=new String[5];
    private Usuario usuarioConsultado;
    ArchivosUsuarios archivosUsuarios;
    
    public MetodosUsuarios()
    {
        arrayUsuario=new ArrayList <Usuario>();
        usuarioConsultado=null;
        archivosUsuarios=new ArchivosUsuarios();
        pasarInformacionArray();
    }
    public void verificarArchivo()//Si el metodo existeArchivo()devuelve true entra a leer los datos del archivo si 
    {                             //devuleve false crea el archivo.
     boolean verificar=archivosUsuarios.existeArchivo();
     if(verificar)
     {
       arrayUsuario=archivosUsuarios.leerInformacionCompleta();
       System.out.println("Se cargaron los datos al archivo: usuario.");
     }else{
       archivosUsuarios.crearArchivo();
     }
    }
    public void guardarArchivo()//Escribe la informacion en el archivo usuario creado.
    {
       archivosUsuarios.crearArchivo();
       for(int contador=0; contador<arrayUsuario.size();contador++){
           System.err.println("...............................nombre "+arrayUsuario.get(contador).getNombreCompleto());
           archivosUsuarios.escribirInformacionEnElArchivo(arrayUsuario.get(contador));
       }
    }
    public Usuario obtenerUsuarioConsultado()//Para verificar el usuario en la pantalla de login
    {
        return this.usuarioConsultado;
    }
    public void agregarUsuario(String informacion[])//Agrega la información del usuario en el array
    {
        Usuario temporal=new Usuario(informacion[0], informacion[1], informacion[2], informacion[3], informacion[4]);
        arrayUsuario.add(temporal);
    }
    
    public void mostrarInformacionUsuario()//Recorre el array y muestra la información almacenada
    {
        for(int contador=0;contador<arrayUsuario.size();contador++)
        {
            System.out.println(arrayUsuario.get(contador).getInformation());
        }
    }
    public boolean consultar(String nombreUsuario)//Recorre el array buscando la informacion segun el nombre de usuario indicado.
    {                                             //Retorna false si no hay información para ese usuario.
        boolean existe = false;
        int tamanioArray = arrayUsuario.size();
        
        for(int contador=0; contador<tamanioArray; contador++)
        {
            if(nombreUsuario.equals(arrayUsuario.get(contador).getNombreUsuario()))
            {
                usuarioConsultado = arrayUsuario.get(contador);
                existe = true;
            }
        }
           return existe;
    }
    public boolean existeArchivo()//Llama al metodo de la clase archivosUsuarios 'existeArchivo()'.
    {
        return this.archivosUsuarios.existeArchivo();
    }
    
    public boolean consultarUsuario(String cedula)
    {
        boolean existe=false;
        for(int contador=0;contador<arrayUsuario.size();contador++)
        {
            if(arrayUsuario.get(contador).getCedula().equals(cedula))
            {
                arregloInformacionConsultada[0]=arrayUsuario.get(contador).getCedula();
                arregloInformacionConsultada[1]=arrayUsuario.get(contador).getNombreCompleto();
                arregloInformacionConsultada[2]=arrayUsuario.get(contador).getNombreUsuario();
                arregloInformacionConsultada[3]=arrayUsuario.get(contador).getContrasenia();
                arregloInformacionConsultada[4]=arrayUsuario.get(contador).getTipo();
                existe=true;
            }
        }
            return existe;
    }
    public void modificarUsuario(String arreglo[])
    {
        for(int contador=0;contador<arrayUsuario.size();contador++)
        {
            if(arrayUsuario.get(contador).getCedula().equals(arreglo[0]))
            {
                arrayUsuario.get(contador).setNombreCompleto(arreglo[1]);
                arrayUsuario.get(contador).setNombreUsuario(arreglo[2]);
                arrayUsuario.get(contador).setContrasenia(arreglo[3]);
                arrayUsuario.get(contador).setTipo(arreglo[4]);
            }
        }
    }
    public void eliminarUsuario(String arreglo[])
    {
        for(int contador=0;contador<arrayUsuario.size();contador++)
        {
            if(arrayUsuario.get(contador).getCedula().equals(arreglo[0]))
            {
                arrayUsuario.remove(contador);
            }
        }
    }
    public String[] getArregloInformacion()
    {
        return this.arregloInformacionConsultada;
    } 
    public void cargarDatosDeArchivo()
    {
        this.arrayUsuario = this.archivosUsuarios.leerInformacionCompleta();
    }
    
    public  Boolean verificarDatosUsuario(  String clave,String nombreUsuario)
    {
        for(int i=0; i<arrayUsuario.size(); i++)
        {
            if(arrayUsuario.get(i).getNombreUsuario().equals(nombreUsuario)&&
                arrayUsuario.get(i).getContrasenia().equals(clave))
            {
                return true;
            }
        }
        return  false;
    }
    
    public void pasarInformacionArray()
    {
        if(archivosUsuarios.existeArchivo()){
            arrayUsuario=archivosUsuarios.leerInformacionCompleta();
            
        }
    }
}
