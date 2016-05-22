/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
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
public class ArchivosCurso_XML {

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz, principal;
    String arregloInformacion[];
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;
    boolean usuarios;

    public ArchivosCurso_XML() {
        nombreArchivo = "Cursos";

        if (cargarXML()) {
            usuarios = true; // ya existe usuario
            // ventana.mostrarMensaje("Ya existe un archivo XML creado, ya fue cargado y puede proceder a utilizarlo");
        } else {
            usuarios = false;// no existe usuarios
            crearXML();
            // ventana.mostrarMensaje("No existía un archivo XML creado, ya fue creado y puede proceder a utilizarlo");
        }

        arregloInformacion = new String[4];
        titulos = new ArrayList();
        valores = new ArrayList();
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

            NodeList nList = document.getElementsByTagName("Curso");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;

        } catch (Exception e) {
            System.out.println("Error al cargar el archivo XML" + e);
        }
        return cargo;
    }

    public void guardarEnXML(Cursos cursos)//Método nuevo en pruebas
    {
        try {

            raiz = document.createElement("Cursos");
            principal = document.createElement("Cursos");
            document.getDocumentElement().appendChild(raiz);

            //crea el elemento sigla
            Element valor1 = document.createElement("sigla");
            //Almacena un valor en la variable text, este valor es el que viene en la posicion cero(sigla)
            Text text = document.createTextNode(cursos.getSigla());
            Element valor2 = document.createElement("nombre");
            Text text2 = document.createTextNode(cursos.getNombre());
            Element valor3 = document.createElement("creditos");
            Text text3 = document.createTextNode(""+cursos.getCreditos());
             Element valor4 = document.createElement("horario");
            Text text4 = document.createTextNode(cursos.getHorario());

            //le aniade un nuevo hijo a la raiz
            raiz.appendChild(valor1);
            valor1.appendChild(text);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);
            raiz.appendChild(valor3);
            valor3.appendChild(text3);
            raiz.appendChild(valor4);
            valor4.appendChild(text4);
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);

        } catch (Exception e) {
            System.err.println("Error al guardar: " + e);
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
            Logger.getLogger(Metodos_Cursos_XML.class.getName()).log(Level.SEVERE, null, ex);

        } catch (TransformerException ex) {
            Logger.getLogger(Metodos_Cursos_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean consultarInformacionDelXml(String sigla) {
        Element raiz = document.getDocumentElement();//guarda el documento en la raiz
        //Cada Cursos es un item
        NodeList listaDeItems = raiz.getElementsByTagName("Cursos");
        Node tag = null, datoContenido = null;
        boolean itemEncontrado = false, tituloSigla = false;
        int contador = 0;

        //va a recorrer la lista de Cursos(items)
        for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
            Node item = listaDeItems.item(contadorItems);
            NodeList datosItem = item.getChildNodes();
            for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                tag = datosItem.item(contadorTags); 
                datoContenido = tag.getFirstChild();

                if (tag.getNodeName().equals("sigla") && datoContenido.getNodeValue().equals("" + sigla)) {
                    itemEncontrado = true;
                }
                if (itemEncontrado && contador < 4) {
                    arregloInformacion[contador] = datoContenido.getNodeValue();
                    contador++;
                }
            }

        }
        return itemEncontrado;
    }

    public String[] getArregloInformacion() {
        return this.arregloInformacion;
    }

    public void modificarInformacionDelXml(String informacion[]) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Cursos");
        Node tag = null, datoContenido = null;
        String arregloInformacion[] = new String[3];
        boolean itemEncontrado = false, tituloSigla = false;
        int contador = 0;
        try {
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);//Almacena un curso

                NodeList datosItem = item.getChildNodes();//Almacena los datos del Cursos en una lista

                //recorre los datos del Cursos
                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    /*
                     Obtiene los datos previamente almacenados en la lista "datosItem",
                     del estudiante, en cada posicion dada por el for
                     */
                    tag = datosItem.item(contadorTags); //obtiene sigla(hijo)
                    datoContenido = tag.getFirstChild();//obtiene el valor de la sigla
                    if (tag.getNodeName().equals("sigla") && datoContenido.getNodeValue().equals("" + informacion[0])) {
                        itemEncontrado = true;
                    }
                    if (itemEncontrado && contador < 4) {
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

    public void eliminarInformacionDelXml(String sigla) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Cursos");
        Node tag = null, datoContenido = null;
        boolean itemEncontrado = false, tituloSigla = false;

        try {
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    tag = datosItem.item(contadorTags);
                    datoContenido = tag.getFirstChild();
                    if (tag.getNodeName().equals("sigla") && datoContenido.getNodeValue().equals("" + sigla)) {
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

    public boolean existeArchivoCursos() {
        boolean existe = false;
        File archivo = new File("Cursos.xml");

        if (archivo.exists()) {
            System.out.println("Existe archivo cursos &**");
            return true;
        }
        System.out.println("no Existe archivo cursos ???");
        return existe;
    }




}
