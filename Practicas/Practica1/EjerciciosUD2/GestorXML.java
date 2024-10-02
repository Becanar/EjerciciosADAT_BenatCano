import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorXML {

    private static final String RUTA_OLIMPIADAS="archivos/olimpiadas.csv";
    private static final String RUTA_XML_OLIMPIADAS ="archivos/olimpiadas.xml" ;
    private static Scanner sc = new Scanner(System.in);
    public static void menu() {
        sc=new Scanner(System.in);
        boolean seguir=true;
        while(seguir){
            System.out.println("------MENÚ XML------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear fichero olimpiadas.xml");
            System.out.println("2.Crear fichero deportistas.xml");
            System.out.println("3.Listar olimpiadas");
            System.out.println("4.Salir");
            try {
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion){
                    case 1:{generarOlimpiadas();break;}
                    case 2:{generarDeportes();break;}
                    case 3:{listarOlimpiadas();break;}
                    case 4:{seguir=false;break;}
                    default:{System.out.println("ERROR! OPCIÓN NO VÁLIDA!");break;}
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN!");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static void generarOlimpiadas() { //COMO EL FICHERO OLIMPIADAS.CSV VIENE CON ERRORES, NO HAY OTRO MODO Y HAY QUE ARRASTRARLOS
        File csvFile = new File(RUTA_OLIMPIADAS);
        if (!csvFile.exists()) {
            System.out.println("El fichero CSV no existe.");
            return;
        }

        ArrayList<String[]> olimpiadas = new ArrayList<String[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 4) {
                    olimpiadas.add(datos);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            return;
        }

        olimpiadas.sort(Comparator.comparing((String[] o) -> o[1])
                .thenComparing(o -> o[2]));

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("olimpiadas");
            doc.appendChild(rootElement);

            for (String[] olimpiada : olimpiadas) {
                Element olimpiadaElement = doc.createElement("olimpiada");
                olimpiadaElement.setAttribute("year", olimpiada[1].replaceAll("\"",""));

                Element juegosElement = doc.createElement("juegos");
                juegosElement.appendChild(doc.createTextNode(olimpiada[0].replaceAll("\"","")));
                olimpiadaElement.appendChild(juegosElement);

                Element temporadaElement = doc.createElement("temporada");
                temporadaElement.appendChild(doc.createTextNode(olimpiada[2].replaceAll("\"","")));
                olimpiadaElement.appendChild(temporadaElement);

                Element ciudadElement = doc.createElement("ciudad");
                ciudadElement.appendChild(doc.createTextNode(olimpiada[3].replaceAll("\"","")));
                olimpiadaElement.appendChild(ciudadElement);

                rootElement.appendChild(olimpiadaElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RUTA_XML_OLIMPIADAS));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Fichero XML de olimpiadas creado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el archivo XML: " + e.getMessage());
        }
    }

    private static void generarDeportes() {
    }

    private static void listarOlimpiadas() {
    }

    public static void main(String[] args) {
        menu();
    }
}
