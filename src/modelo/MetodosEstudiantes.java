package modelo;

import java.util.ArrayList;
/**
 *
 * @author tecnologiamultimedia
 */
public class MetodosEstudiantes {
    
    private ArrayList <Estudiante> arrayEstudiantes;
    String arregloInformacionConsultada[]=new String[2];
    ArchivosEstudiantes archivosEstudiantes;
    
    public MetodosEstudiantes()
    {
        arrayEstudiantes=new ArrayList <Estudiante>();
        archivosEstudiantes=new ArchivosEstudiantes();
        
        verificarArchivo();
    }
    public void verificarArchivo()//Si el metodo existeArchivo()devuelve true entra a leer los datos del archivo si 
    {                             //devuleve false crea el archivo.
     boolean verificar=archivosEstudiantes.existeArchivo();
     if(verificar)
     {
       arrayEstudiantes=archivosEstudiantes.leerInformacionCompleta();
       System.out.println("Se cargaron los datos al archivo: estudiante.");
     }else{
       archivosEstudiantes.crearArchivo();
     }
    }
    public void guardarArchivo()//Escribe la informacion en el archivo creado.
    {
       archivosEstudiantes.crearArchivo();
       for(int contador=0; contador<arrayEstudiantes.size();contador++){
           archivosEstudiantes.escribirInformacionEnElArchivo(arrayEstudiantes.get(contador));
       }
    }
    public void agregarEstudiante(String informacion[])//Agrega la información del estudiante en el array
    {
        Estudiante temporal=new Estudiante(informacion[0], informacion[1], informacion[2]);
        arrayEstudiantes.add(temporal);
    }
    
    public void mostrarInformacion()//Recorre el array y muestra la información almacenada
    {
        for(int contador=0;contador<arrayEstudiantes.size();contador++)
        {
            System.out.println(arrayEstudiantes.get(contador).getInformacion());
        
        }
    }
    public boolean consultarEstudiante(String cedula)//Recorre el array buscando la informacion segun la cedula indicada.
    {                                                //Retorna false si no hay información para esa cedula.
        boolean existe=false;
        for(int contador=0;contador<arrayEstudiantes.size();contador++)
        {
            if(arrayEstudiantes.get(contador).getCedula().equals(cedula))
            {
                arregloInformacionConsultada[0]=arrayEstudiantes.get(contador).getNombrecompleto();
                arregloInformacionConsultada[1]=arrayEstudiantes.get(contador).getDireccion();
                existe=true;
            }
        }
        return existe;
    }
    
    public void modificarEstudiante(String arreglo[])//Recorre el array y si la cedula almacenada es igual a la posición del vector
    {                                                //que recibe por parametro, entra a modificar los campos de nombre, y dirección.
        for(int contador=0;contador<arrayEstudiantes.size();contador++)
        {
            if(arrayEstudiantes.get(contador).getCedula().equals(arreglo[0]))
            {
                arrayEstudiantes.get(contador).setNombrecompleto(arreglo[1]);
                arrayEstudiantes.get(contador).setDireccion(arreglo[2]);
                
            }
        }
    }
    
    public void eliminarEstudiante(String arreglo[])//Elimina del array la información de la cedula 
    {                                               //guardada en la posicion indicada del vector
        for(int contador=0;contador<arrayEstudiantes.size();contador++)
        {
            if(arrayEstudiantes.get(contador).getCedula().equals(arreglo[0]))
            {
                arrayEstudiantes.remove(contador);
            }
        }
    }
    public String[] getArregloInformacion()//Devuelve el vector con la informacion cargada
    {
        return this.arregloInformacionConsultada;
    }   
}
