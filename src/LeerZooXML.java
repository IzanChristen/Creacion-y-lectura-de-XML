import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerZooXML {
    public static void main(String[] args) {
        try {
            File archivo = new File("zoo.xml");
            if (archivo.exists()){
                System.out.println("zoo.xml encontrado");
            }
            else {
                System.out.println("zoo.xml no encontrado");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = factory.newDocumentBuilder();
            Document document = constructor.parse(archivo);

            document.getDocumentElement().normalize();

            NodeList lista = document.getElementsByTagName("animal");
            int total = lista.getLength();

            for (int i = 0; i < total; i++) {
                Node nodo = lista.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element animal = (Element) nodo;

                    String id = animal.getAttribute("id");
                    String nombre = animal.getElementsByTagName("nombre").item(0).getTextContent();
                    String especie = animal.getElementsByTagName("especie").item(0).getTextContent();
                    String alimento = animal.getElementsByTagName("alimento").item(0).getTextContent();
                    String edad = animal.getElementsByTagName("edad").item(0).getTextContent();

                    System.out.println( "\nAnimal #: "+id+
                                        "\nNombre: "+nombre+
                                        "\nEspecie: "+especie+
                                        "\nAlimento: "+alimento+
                                        "\nEdad: "+edad);
                }
            }

            System.out.println("\nEl total de animales es: "+total);
            System.out.println("\nLos animales que comen carne son:");

            int numCarne = 0;

            for (int i = 0; i < total; i++) {
                Element animal = (Element) lista.item(i);
                String alimento = animal.getElementsByTagName("alimento").item(0).getTextContent();
                if (alimento.equals("Carne")){
                    String nombre = animal.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println(i+": "+nombre);
                    numCarne++;
                }
            }
            if (numCarne != 0){
                System.out.println("Hay "+numCarne+" animal/es con alimento Carne");
            }
            else {
                System.out.println("Ningun animal tiene Carne como alimento");
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
