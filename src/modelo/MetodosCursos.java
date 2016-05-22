package modelo;

import java.util.ArrayList;
/**
 *
 * @author tecnologiamultimedia
 */
public class MetodosCursos {
    
    private ArrayList <Cursos> arrayCursos;
    String arregloInformacionConsultada[]=new String[3];
    ArchivosCursos archivosCursos;
    
    public MetodosCursos()
    {
        arrayCursos=new ArrayList <Cursos>();
        archivosCursos=new ArchivosCursos();
        verificarArchivo();
    }
    public void verificarArchivo()//Si el metodo existeArchivo()devuelve true entra a leer los datos del archivo si 
    {                             //devuleve false crea el archivo.
     boolean verificar=archivosCursos.existeArchivo();
     if(verificar)
     {
       arrayCursos=archivosCursos.leerInformacionCompleta();
       System.out.println("Se cargaron los datos al archivo: cursos.");
     }else{
       archivosCursos.crearArchivo();
     }
    }
    public void guardarArchivo() //Escribe la informacion en el archivo creado.
    {
       archivosCursos.crearArchivo();
       for(int contador=0; contador<arrayCursos.size();contador++){
          archivosCursos.escribirInformacionEnElArchivo(arrayCursos.get(contador));
       }
    }
    public void agregarCurso(String informacion[]) //Agrega la información del curso en el array
    {
        Cursos temporal=new Cursos(informacion[0], informacion[1], Integer.parseInt(informacion[2]), informacion[3]);
        arrayCursos.add(temporal);
    }
    public void mostrarInformacion() //Recorre el array y muestra la información almacenada
    {
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            //System.out.println(arrayCursos.get(contador).getInformacion());
        }
    }
    public boolean consultarCurso(String sigla)//Recorre el array buscando la informacion segun la sigla indicada.
    {                                          //Retorna false si no hay información para esa sigla.
        boolean existe=false;
        
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(sigla))
            {
                arregloInformacionConsultada[0]=arrayCursos.get(contador).getNombre();
                arregloInformacionConsultada[1]=""+arrayCursos.get(contador).getCreditos();
                arregloInformacionConsultada[2]=arrayCursos.get(contador).getHorario();
                existe=true;
            }
        }
           return existe;
    }
    public void modificarCurso(String arreglo[]) //Recorre el array y si la sigla almacenada es igual a la posición del vector
    {                                            //que recibe por parametro, entra a modificar los campos de nombre, creditos y horario.
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(arreglo[0]))
            {
                arrayCursos.get(contador).setNombre(arreglo[1]);
                arrayCursos.get(contador).setCreditos(Integer.parseInt(arreglo[2]));
                arrayCursos.get(contador).setHorario(arreglo[3]);
            }
        }
    }
    public void eliminarCurso(String arreglo[])//Elimina del array la información de la sigla 
    {                                          //guardada en la posicion indicada del vector
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(arreglo[0]))
            {
                arrayCursos.remove(contador);
            }
        }
    }
    public String[] getArregloInformacion()//Devuelve el vector con la informacion cargada
    {
        return this.arregloInformacionConsultada;
    } 
}
