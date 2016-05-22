/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
//proyecto original
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author JenifferHR
 */
public class Archivo_Matricula_XML {

  
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz, principal, raizDetalleMatricula;
    String arregloInformacion[];
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;
    private ArrayList<String> listaDeSiglas;
    boolean usuarios;

    public Archivo_Matricula_XML() {
        nombreArchivo = "Matricula";

        if (cargarXML()) {
            usuarios = true; // ya existe usuario
            // ventana.mostrarMensaje("Ya existe un archivo XML creado, ya fue cargado y puede proceder a utilizarlo");
        } else {
            usuarios = false;// no existe usuarios
            crearXML();
            // ventana.mostrarMensaje("No existía un archivo XML creado, ya fue creado y puede proceder a utilizarlo");
        }

        arregloInformacion = new String[3];
        titulos = new ArrayList();
        valores = new ArrayList();
        listaDeSiglas= new ArrayList<>();
    }

    public boolean devolverExistenciaUsuarios() {
        return usuarios;
    }

    public void crearXML() //Método nuevo en pruebas
    {
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "xml", null);
            document.setXmlVersion("1.0");
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));

            console = new StreamResult(System.out);

            transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(source, result);
            transformer.transform(source, console);

        } catch (Exception e) {
            System.err.println("Error al crear el archivo XML: " + e);
        }
    }

    public boolean cargarXML() //Método nuevo en pruebas
    {
        boolean cargo = false;
        try {

            File fXmlFile = new File(nombreArchivo + ".xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(fXmlFile);
            document.getDocumentElement().normalize();
            cargo = true;

            NodeList nList = document.getElementsByTagName("Matricula");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;

        } catch (Exception e) {
            System.out.println("Error al cargar el archivo XML" + e);
        }
        return cargo;
    }

    public Boolean guardarEnXML(Matricula matricula, ArrayList<String> arraySiglas)//Método nuevo en pruebas
    {
        try {

            raiz = document.createElement("Matricula");// crea la raiz
            raizDetalleMatricula = document.createElement("Detalle_Matricula");//crea un detalle matricula
            principal = document.createElement("Matricula");
            document.getDocumentElement().appendChild(raiz);

            //crea el elemento cedula
            Element valor1 = document.createElement("codigo");
            //Almacena un valor en la variable text, este valor es el que viene en la posicion cero(cedula)
            Text text = document.createTextNode(matricula.getCodigo());
            Element valor2 = document.createElement("cedula");
            Text text2 = document.createTextNode(matricula.getCedula());

            //le aniade un nuevo hijo a la raiz
            raiz.appendChild(valor1);
            valor1.appendChild(text);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);

            /*
            *
            Se creo un document.createElement("Detalle_Matricula"); 
            Entonces por cada matricula hay un Detalle Matricula , el cual tiene siglas
            y se recorre el array para añadir al detalle todas las siglas que se encontraron en la tabla cuando
            se presiono el boton finalizar
            */
            for (int contador = 0; contador < arraySiglas.size(); contador++) {
                
                // Crea un valor3 y de nombre le da sigla + el contador , porque no pueden haber hijos con igual nombre 
                Element valor3 = document.createElement("sigla" + contador);
                Text text3 = document.createTextNode(arraySiglas.get(contador));
                //Agrega al detalle matricula el nuevo hijo
                raizDetalleMatricula.appendChild(valor3);
                valor3.appendChild(text3);

            }
            // Añade a la raiz el detalle matricula 
            raiz.appendChild(raizDetalleMatricula);
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar: " + e);

            return false;
        }

    }

    public void crearArchivo(String nombreArchivo) {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, nombreArchivo, null);
            document.setXmlVersion("1.0");
            raiz = document.getDocumentElement();
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("Archivo XML creado con el nombre: " + nombreArchivo);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Metodos_Estudiante_XML.class.getName()).log(Level.SEVERE, null, ex);

        } catch (TransformerException ex) {
            Logger.getLogger(Metodos_Estudiante_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean consultarInformacionDelXml(String codigo) {
        Element raiz = document.getDocumentElement();//guara el documento en la raiz
        //Cada estudiante es un item
        int numero=0;
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        Node tag = null, datoContenido = null;
        
        boolean itemEncontrado = false, verificarNodo = false;
        int contador = 0;

        //va a recorrer la lista de estudiantes(items)
        int numeroSiglas = 0;
        for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
            Node item = listaDeItems.item(contadorItems);//toma una matricula de la lista
            NodeList datosItem = item.getChildNodes();//toma los hijos de esa matricula
            for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                tag = datosItem.item(contadorTags);
                datoContenido = tag.getFirstChild();

                // Pregunta si el hijo de la matricula es codigo y ese codigo es igual al que le ingresa
                if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + codigo)) {
                    itemEncontrado = true;// Encontro la matricula
                    verificarNodo=true;//encontro el nodo que va a servir para el detalle de matricula
                    numero=contadorItems;//Guarda la posicion enque se encontro la matricula
                }
                else
                {
                    verificarNodo=false;
                }
                if (itemEncontrado && contador < 2) {
                    arregloInformacion[contador] = datoContenido.getNodeValue();
                    contador++;
                }
                    listaDeSiglas= new ArrayList<>();
                  // Se tiene un detalle_Matricula por cada Matricula , entonces se hace lo mismo que para matricula
                //Recorra a detalle matricula
                    // Verifica que se encontrara el codigo matricula y que el nodo en el que esta se llame Detalle_Matricula
                if (itemEncontrado&&tag.getNodeName().equals("Detalle_Matricula")) {
                    //igual a listaDeItems de matricula
                   NodeList listaSigla = raiz.getElementsByTagName("Detalle_Matricula");
                    for(int i=0; i<listaSigla.getLength(); i++)
                    {
                        
                        Node item2 =listaSigla.item(i);//toma un detalle matricula
                        NodeList datosItem2=item2.getChildNodes();//toma los hijos de es detalle matricula
                        Node tag2=null,datoContenido2=null;
                        //Recorre esos hijos de detalle matricula
                        for(int contadorTags2=0; contadorTags2<datosItem2.getLength(); contadorTags2++)
                        {
                            tag2=datosItem2.item(contadorTags2);//toma el hijo en la posicion del for 
                            datoContenido2=tag2.getFirstChild();//Toma toma el primer hijo de ese hijo
                            
                           // verifica si la posicion de ese detalle matricula es igual a la posicion donde se encontro
                            // la matricula madre 
                            if(i==numero && !verificarNodo)
                            {
                                // agrega la sigla que se encuntra ahi
                                listaDeSiglas.add(datoContenido2.getNodeValue());
                            }
                            
                        }
                    }
                    
                }
            }

        }
        return itemEncontrado;
    }

    public String[] getArregloInformacion() {
        return this.arregloInformacion;
    }

    public ArrayList<String> getListaSiglas() {
        return listaDeSiglas;
    }

    public void modificarInformacionDelXml(String informacion[]) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        Node tag = null, datoContenido = null;
        String arregloInformacion[] = new String[3];
        boolean itemEncontrado = false, tituloCedula = false;
        int contador = 0;
        try {
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);//Almacena un estudiante

                NodeList datosItem = item.getChildNodes();//Almacena los datos del estudiante en una lista

                //recorre los datos del estudiante
                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    /*
                     Obtiene los datos previamente almacenados en la lista "datosItem",
                     del estudiante, en cada posicion dada por el for
                     */
                    tag = datosItem.item(contadorTags); //obtiene cedula(hijo)
                    datoContenido = tag.getFirstChild();//obtiene el valor de la cedula
                    if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + informacion[0])) {
                        itemEncontrado = true;
                    }
                    if (itemEncontrado && contador < 3) {
                        datoContenido.setNodeValue(informacion[contador]);
                        contador++;
                    }
                }
            }
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e);
        }
    }

    public void eliminarInformacionDelXml(String codigo) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        Node tag = null, datoContenido = null;
        boolean itemEncontrado = false, tituloCedula = false;

        try {
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    tag = datosItem.item(contadorTags);
                    datoContenido = tag.getFirstChild();
                    if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + codigo)) {
                        itemEncontrado = true;
                        raiz.removeChild(item);
                        source = new DOMSource(document);
                        result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
                        console = new StreamResult(System.out);
                        transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(source, result);
                        transformer.transform(source, console);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar: " + e);
        }
    }

    public boolean elimiar(String codigo, String sigla) {
        Element raiz = document.getDocumentElement();//guara el documento en la raiz
        //Cada estudiante es un item
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");

        Node tag = null, datoContenido = null;
        boolean itemEncontrado = false, tituloCedula = false;
        int contador = 0;

        try {//va a recorrer la lista de estudiantes(items)
            int numeroSiglas = 0;
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    tag = datosItem.item(contadorTags);
                    datoContenido = tag.getFirstChild();

                    if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + codigo)) {
                        itemEncontrado = true;
                    }
                    //Recorra a detalle matricula
                    if (itemEncontrado && tag.getNodeName().equals("Detalle_Matricula")) {
                        NodeList listaSigla = raiz.getElementsByTagName("Detalle_Matricula");
                        for (int i = 0; i < listaSigla.getLength(); i++) {
                            if (listaSigla.item(i).getChildNodes().item(i).getFirstChild().getNodeValue().equals(sigla));
                            {
                                // raiz.removeChild(listaSigla.item(i).getChildNodes().item(i).getFirstChild());
                                source = new DOMSource(document);
                                result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
                                console = new StreamResult(System.out);
                                transformer = TransformerFactory.newInstance().newTransformer();
                                transformer.transform(source, result);
                                transformer.transform(source, console);
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        return itemEncontrado;
    }

    public String obtenerCodigo() {
        String cod = "00001";
        Element raiz = document.getDocumentElement();//guara el documento en la raiz
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        
        
        if (listaDeItems.getLength()>0) 
        { 
            int numeroMatriculas = listaDeItems.getLength() + 1;
            if (numeroMatriculas < 10) {
                cod = "0000" + numeroMatriculas;
            }
            else if (numeroMatriculas >= 10 && numeroMatriculas < 100) {
                
                cod = "000" + numeroMatriculas;
            }
            else if(numeroMatriculas>=100 && numeroMatriculas<1000)
            {
                cod = "00" + numeroMatriculas;
            }
            else if(numeroMatriculas>=1000 && numeroMatriculas<10000)
            {
                 cod = "0" + numeroMatriculas;
            }
            else
            {
                cod=""+numeroMatriculas;
            }
        }
        return cod;
    }


}
