import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CrearZooXML {
    public static void main(String[] args) {
        try{
            //Documento XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document document = constructor.newDocument();

            //Raiz
            Element raiz = document.createElement("zoo");
            document.appendChild(raiz);

            //Animales
            raiz.appendChild(crearAnimal(document,"1","Pinguino","Aptenodytes forsteri","Pez",5));
            raiz.appendChild(crearAnimal(document,"2","Leon","Panthera leo","Carne",8));

            //Crear XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult resultado = new StreamResult(new File("zoo.xml"));

            transformer.transform(source, resultado);

            System.out.println("zoo.xml ha sido generado correctamente");
        }
        //Excepciones de parse y transformer
        catch (ParserConfigurationException | TransformerException e){
            //Metodo diferente para mostrar el error junto a la linea de codigo
            e.printStackTrace();
            System.out.println("zoo.xml NO ha sido generado correctamente");
        }
    }

    //Metodo crear animales
    private static Element crearAnimal(Document doc, String id, String nombre, String especie, String alimento, int edad){
        Element animal = doc.createElement("animal");
        animal.setAttribute("id", id);

        Element nombr = doc.createElement("nombre");
        nombr.appendChild(doc.createTextNode(nombre));
        animal.appendChild(nombr);

        Element especi = doc.createElement("especie");
        especi.appendChild(doc.createTextNode(especie));
        animal.appendChild(especi);

        Element aliment = doc.createElement("alimento");
        aliment.appendChild(doc.createTextNode(alimento));
        animal.appendChild(aliment);

        Element eda = doc.createElement("edad");
        eda.appendChild(doc.createTextNode(String.valueOf(edad)));
        animal.appendChild(eda);

        return animal;
    }
}