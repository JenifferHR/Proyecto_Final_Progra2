package modelo;

import controlador.Controlador_FRM_Matricula;
import java.util.ArrayList;
import vista.FRM_Matricula;

/**
 *
 * @author vaio01
 */
public class MetodosMatricula {

    private ArrayList<Matricula> arrayMatricula;
    String arregloInformacionConsultada[] = new String[4];
    ArchivosMatricula archivosMatricula;
    Controlador_FRM_Matricula controlador;
    MetodosCursos metodosCursos;
    MetodosEstudiantes metodosEstudiantes;
    FRM_Matricula frm_Matricula;
  
    public MetodosMatricula() {
        arrayMatricula = new ArrayList<Matricula>();
        archivosMatricula = new ArchivosMatricula();
        verificarArchivo();
    }
    public MetodosMatricula(MetodosEstudiantes metodosEstudiantes,MetodosCursos metodosCursos,Controlador_FRM_Matricula controlador, FRM_Matricula frm_Matricula){
     arrayMatricula=new ArrayList<Matricula>();
     this.metodosCursos=metodosCursos;
     this.metodosEstudiantes=metodosEstudiantes;
     this.controlador=controlador;
     this.frm_Matricula=frm_Matricula;
     archivosMatricula=new ArchivosMatricula();
     
     verificarArchivo();
    }
    public void verificarArchivo()//Si el metodo existeArchivo()devuelve true entra a leer los datos del archivo si 
    {                             //devuleve false crea el archivo.
        boolean verificar = archivosMatricula.existeArchivo();
        if (verificar) {
            arrayMatricula = archivosMatricula.leerInformacionCompleta();
            System.out.println("Se cargaron los datos al archivo: matricula.");
        } else {
            archivosMatricula.crearArchivo();
        }
    }

    public void guardarArchivo()//Escribe la informacion en el archivo creado.
    {
        archivosMatricula.crearArchivo();
        for (int contador = 0; contador < arrayMatricula.size(); contador++) {
            archivosMatricula.escribirInformacionEnElArchivo(arrayMatricula.get(contador));
        }
    }

    public void agregarMatricula(String informacion[])//Agrega la información de la matricula en el array
    {
        Matricula temporal = new Matricula(informacion[0], informacion[1], informacion[2], true);
        arrayMatricula.add(temporal);
        
    }

    public void mostrarInformacion()//Recorre el array y muestra la información almacenada
    {
        for (int contador = 0; contador < arrayMatricula.size(); contador++) {
            System.out.println(arrayMatricula.get(contador).infoMatricula());
        }
    }

    public boolean consultarMatricula(String codigo)//Recorre el array buscando la informacion segun el codigo indicado y la
    {                         //carga en el arregloInformacionConsultada. Retorna false si no hay información para ese codigo.
        boolean existe = false;

        for (int contador = 0; contador < arrayMatricula.size(); contador++) {
//            if (arrayMatricula.get(contador).getCodigo().equals(codigo) && (arrayMatricula.get(contador).getEstado() == true)) {
//                arregloInformacionConsultada[0] = codigo;
//                arregloInformacionConsultada[1] = arrayMatricula.get(contador).getCedula();
//                arregloInformacionConsultada[2] = arrayMatricula.get(contador).getSigla();
//                existe = true;
//            }
             if(arrayMatricula.get(contador).getCodigo().equals(codigo)&&(arrayMatricula.get(contador).getEstado()==true))
            {
                arregloInformacionConsultada[0]=codigo;
                arregloInformacionConsultada[1]=arrayMatricula.get(contador).getCedula();
                metodosEstudiantes.consultarEstudiante(arrayMatricula.get(contador).getCedula());
                arregloInformacionConsultada[2]=metodosEstudiantes.getArregloInformacion()[0];
                arregloInformacionConsultada[3]=arrayMatricula.get(contador).getSigla();
                frm_Matricula.agregarInformacionTabla(arregloInformacionConsultada);
                existe=true;
            }
        }
        return existe;
    }

    public void modificarMatricula(String arreglo[])//Recorre el array y si el codigo almacenado es igual a la posición del vector
    {                                                //que recibe por parametro, entra a modificar los campos cedula y sigla.
        for (int contador = 0; contador < arrayMatricula.size(); contador++) {
            if (arrayMatricula.get(contador).getCodigo().equals(arreglo[0])) {
                arrayMatricula.get(contador).setCedula(arreglo[1]);
                arrayMatricula.get(contador).setSigla(arreglo[2]);
            }
        }
    }

   
    public void eliminarMatriculaPorCurso(String codigo, String siglas)//Elimina la informacion del vector segun la fila seleccionada
    {                                                                  //pero se conserva el codigo
        for (int i = 0; i < arrayMatricula.size(); i++) {
            if (codigo.equals(arrayMatricula.get(i).getCodigo()) && siglas.equals(arrayMatricula.get(i).getSigla())) {
                arrayMatricula.get(i).setEstado(false);
            }
        }
    }
//    public void eliminarMatricula1(String arreglo[]) //Elimina todo de la ventana xq se le pasa el vector
//    {                                                //pero no borra del array
//      for(int contador=0;contador<arrayMatricula.size();contador++)
//        {
//            if(arrayMatricula.get(contador).getCodigo().equals(arreglo[0]))
//            {
//                arrayMatricula.get(contador).setEstado(false);
//            }
//        }
//    }
//    public void eliminarMatricula(String arreglo[])//Elimina del array
//    {
//        for(int contador=0;contador<arrayMatricula.size();contador++)
//        {
//            if(arrayMatricula.get(contador).getCodigo().equals(arreglo[0]))
//            {
//                arrayMatricula.remove(contador);
//            }
//        }
//    }

    public String[] getArregloInformacion()//Devuelve el vector con la informacion cargada
    {
        return this.arregloInformacionConsultada;
    }
 public String devolverCodigo(){ //Coloca el codigo consecutivo de las matriculas
        String codigo="0000";
        if(arrayMatricula.size()==0)
        {
          codigo+=1;
        }else{
         int numero=Integer.parseInt(arrayMatricula.get(arrayMatricula.size()-1).getCodigo());
         numero++;
         codigo="0000"+numero;
        }
        codigo=codigo.substring(codigo.length()-5,codigo.length());
        return codigo;
    }
    public String devolverCodigo2() {
        String codigoMatricula = "00001";
        int codigoEntero;

        codigoEntero = arrayMatricula.size() + 1; //Toma el valor del codigo de la ultima fila.

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

        return codigoMatricula;
    }
}
