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
import java.util.*;

public class GestorXML {

    private static final String RUTA_OLIMPIADAS = "archivos/olimpiadas.csv";
    private static final String RUTA_XML_OLIMPIADAS = "archivos/olimpiadas.xml";
    private static final String RUTA_ATLETAS = "archivos/athlete_events_corregido.csv";
    private static final String RUTA_XML_ATLETAS = "archivos/deportistas.xml";
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        sc = new Scanner(System.in);
        boolean seguir = true;
        while (seguir) {
            System.out.println("------MENÚ XML------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear fichero olimpiadas.xml");
            System.out.println("2.Crear fichero deportistas.xml");
            System.out.println("3.Listar olimpiadas");
            System.out.println("4.Salir");
            try {
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1: {
                        generarOlimpiadas();
                        break;
                    }
                    case 2: {
                        generarDeportes();
                        break;
                    }
                    case 3: {
                        listarOlimpiadas();
                        break;
                    }
                    case 4: {
                        seguir = false;
                        break;
                    }
                    default: {
                        System.out.println("ERROR! OPCIÓN NO VÁLIDA!");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN!");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static boolean generarOlimpiadas() {
        File csvFile = new File(RUTA_OLIMPIADAS);
        if (!csvFile.exists()) {
            System.out.println("ERROR! EL FICHERO NO EXISTE!");
            return false;
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
            System.out.println("ERROR!ERROR EN LA ENTRADA/SALIDA");
            return false;
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
                olimpiadaElement.setAttribute("year", olimpiada[1].replaceAll("\"", ""));

                Element juegosElement = doc.createElement("juegos");
                juegosElement.appendChild(doc.createTextNode(olimpiada[0].replaceAll("\"", "")));
                olimpiadaElement.appendChild(juegosElement);

                Element temporadaElement = doc.createElement("temporada");
                temporadaElement.appendChild(doc.createTextNode(olimpiada[2].replaceAll("\"", "")));
                olimpiadaElement.appendChild(temporadaElement);

                Element ciudadElement = doc.createElement("ciudad");
                ciudadElement.appendChild(doc.createTextNode(olimpiada[3].replaceAll("\"", "")));
                olimpiadaElement.appendChild(ciudadElement);

                rootElement.appendChild(olimpiadaElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RUTA_XML_OLIMPIADAS));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("FICHERO XML CREADO CORRECTAMENTE.");
        } catch (Exception e) {
            System.out.println("ERROR! NO SE HA PODIDO CREAR EL FICHERO!");
            return false;
        }
        return true;
    }


    public static boolean generarDeportes() {
        TreeMap<Integer, ArrayList<String[]>> deportistas = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ATLETAS))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                int id = Integer.parseInt(datos[0].replaceAll("\"", ""));

                if (!deportistas.containsKey(id)) {
                    deportistas.put(id, new ArrayList<>());
                }
                deportistas.get(id).add(datos);
            }
        } catch (IOException e) {
            System.out.println("ERROR!ERROR EN LA ENTRADA/SALIDA");
            return false;
        }


        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("deportistas");
            doc.appendChild(rootElement);


            for (int id : deportistas.keySet()) {
                ArrayList<String[]> participaciones = deportistas.get(id);
                String[] primeraParticipacion = participaciones.get(0);


                Element deportistaElement = doc.createElement("deportista");
                deportistaElement.setAttribute("id", ""+id);
                rootElement.appendChild(deportistaElement);


                crearHijo(doc, deportistaElement, "nombre", primeraParticipacion[1].replaceAll("\"", ""));
                crearHijo(doc, deportistaElement, "sexo", primeraParticipacion[2].replaceAll("\"", ""));
                crearHijo(doc, deportistaElement, "altura", primeraParticipacion[4]);
                crearHijo(doc, deportistaElement, "peso", primeraParticipacion[5]);


                Element participacionesElement = doc.createElement("participaciones");
                deportistaElement.appendChild(participacionesElement);


                HashMap<String, ArrayList<String[]>> participacionesPorDeporte = new HashMap<>();

                for (String[] participacion : participaciones) {
                    String deporte = participacion[12].replaceAll("\"", "");
                    if (!participacionesPorDeporte.containsKey(deporte)) {
                        participacionesPorDeporte.put(deporte, new ArrayList<>());
                    }
                    participacionesPorDeporte.get(deporte).add(participacion);
                }


                for (String deporte : participacionesPorDeporte.keySet()) {
                    Element deporteElement = doc.createElement("deporte");
                    deporteElement.setAttribute("nombre", deporte);
                    participacionesElement.appendChild(deporteElement);


                    for (String[] participacion : participacionesPorDeporte.get(deporte)) {
                        Element participacionElement = doc.createElement("participacion");
                        participacionElement.setAttribute("edad", participacion[3]);
                        deporteElement.appendChild(participacionElement);


                        Element equipoElement = doc.createElement("equipo");
                        equipoElement.setAttribute("abbr", participacion[7].replaceAll("\"", ""));
                        equipoElement.appendChild(doc.createTextNode(participacion[6].replaceAll("\"", "")));
                        participacionElement.appendChild(equipoElement);

                        crearHijo(doc, participacionElement, "juegos", participacion[8].replaceAll("\"", "") + " - " + participacion[11].replaceAll("\"", ""));
                        crearHijo(doc, participacionElement, "evento", participacion[13].replaceAll("\"", ""));
                        crearHijo(doc, participacionElement, "medalla", participacion[14].replaceAll("\"", ""));
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RUTA_XML_ATLETAS));

            transformer.transform(source, result);
            System.out.println("FICHERO XML CREADO CORRECTAMENTE.");
        } catch (Exception e) {
            System.out.println("ERROR! NO SE HA PODIDO CREAR EL FICHERO!");
            return false;
        }
        return true;
    }


    private static void crearHijo(Document doc, Element parent, String tagName, String textContent) {
        Element child = doc.createElement(tagName);
        child.appendChild(doc.createTextNode(textContent));
        parent.appendChild(child);
    }

    private static void listarOlimpiadas() {
    }

    public static void main(String[] args) {
        menu();
    }
}
