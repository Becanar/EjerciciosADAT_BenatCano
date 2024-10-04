import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorOlimpiadas2 {
    private static final String RUTA_OLIMPIADAS_BIN = "archivos/olimpiadas.bin";
    private static final String RUTA_XML_OLIMPIADAS = "archivos/olimpiadas.xml";
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        sc = new Scanner(System.in);
        boolean seguir = true;
        while (seguir) {
            System.out.println("------MENÚ XML------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear fichero olimpiadas.bin");
            System.out.println("2.Añadir edición olímpica");
            System.out.println("3.Buscar olimpiadas por sede");
            System.out.println("4.Eliminar edición olímpica");
            System.out.println("5.Salir");
            try {
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1 -> crearFicheroOlimpiadas();
                    case 2 -> anadirEdicionOlimpica();
                    case 3 -> buscarOlimpiadasPorSede();
                    case 4 -> eliminarEdicionOlimpica();
                    case 5 -> seguir = false;
                    default -> System.out.println("ERROR! OPCIÓN NO VÁLIDA!");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN VÁLIDA!");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static boolean crearFicheroOlimpiadas() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ArrayList<Olimpiada> olimpiadas = new ArrayList<>();

            DefaultHandler handler = new DefaultHandler() {
                private Olimpiada olimpiadaActual;
                private StringBuilder data;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("olimpiada")) {
                        int anio = Integer.parseInt(attributes.getValue("year"));
                        olimpiadaActual = new Olimpiada("", anio, "");
                    }
                    data = new StringBuilder();
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    switch (qName.toLowerCase()) {
                        case "olimpiada":
                            olimpiadas.add(olimpiadaActual);
                            break;
                        case "temporada":
                            olimpiadaActual.setTemporada(data.toString().trim());
                            break;
                        case "ciudad":
                            olimpiadaActual.setSede(data.toString().trim());
                            break;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    data.append(new String(ch, start, length));
                }
            };

            saxParser.parse(RUTA_XML_OLIMPIADAS, handler);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_OLIMPIADAS_BIN))) {
                for (Olimpiada olimpiada : olimpiadas) {
                    oos.writeObject(olimpiada);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR! NO SE HA PODIDO CREAR EL ARCHIVO!");
            return false;
        }

        System.out.println("ARCHIVO BIN CREADO CORRECTAMENTE!");
        return true;
    }


    private static boolean anadirEdicionOlimpica() {
        System.out.print("Ingrese la sede: ");
        String sede = sc.nextLine();
        System.out.print("Ingrese el año: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la temporada (Summer/Winter): ");
        String temporada = sc.nextLine();

        Olimpiada nuevaOlimpiada = new Olimpiada(sede, anio, temporada);

        ArrayList<Olimpiada> olimpiadas = leerOlimpiadas();

        olimpiadas.add(nuevaOlimpiada);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_OLIMPIADAS_BIN))) {
            for (Olimpiada olimpiada : olimpiadas) {
                oos.writeObject(olimpiada);
            }
            System.out.println("Edición olímpica añadida con éxito.");
        } catch (IOException e) {
            System.out.println("ERROR! NO SE HA PODIDO GUARDAR EL ARCHIVO!");
            e.printStackTrace();
        }
        return true;
    }

    private static boolean buscarOlimpiadasPorSede() {
        System.out.print("Ingrese la palabra de búsqueda para la sede: ");
        String palabraClave = sc.nextLine().toLowerCase();
        ArrayList<Olimpiada> olimpiadasEncontradas = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_OLIMPIADAS_BIN))) {
            while (true) {
                try {
                    Olimpiada olimpiada = (Olimpiada) ois.readObject();

                    if (olimpiada.getSede().toLowerCase().contains(palabraClave)) {
                        olimpiadasEncontradas.add(olimpiada);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR! NO SE HA PODIDO LEER EL ARCHIVO!");
            e.printStackTrace();
            return false;
        }

        if (olimpiadasEncontradas.isEmpty()) {
            System.out.println("No se encontraron olimpiadas para la sede proporcionada.");
        } else {
            System.out.println("Olimpiadas encontradas:");
            for (Olimpiada olimpiada : olimpiadasEncontradas) {
                System.out.println(olimpiada);
            }
        }
        return true;
    }


    private static boolean eliminarEdicionOlimpica() {
        System.out.print("Ingrese el año de la edición olímpica a eliminar: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la temporada (Summer/Winter): ");
        String temporada = sc.nextLine();

        ArrayList<Olimpiada> olimpiadas = leerOlimpiadas();

        boolean encontrado = false;
        for (Olimpiada olimpiada : olimpiadas) {
            if (olimpiada.getAnio() == anio && olimpiada.getTemporada().equalsIgnoreCase(temporada)) {
                System.out.println("Olimpiada encontrada: " + olimpiada);
                System.out.print("¿Está seguro de que desea eliminar esta edición olímpica? (S/N): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("S")) {
                    olimpiadas.remove(olimpiada);
                    encontrado = true;
                    System.out.println("Edición olímpica eliminada.");
                    break;
                } else {
                    System.out.println("Eliminación cancelada.");
                    return false;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ninguna edición olímpica con el año y temporada proporcionados.");
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_OLIMPIADAS_BIN))) {
            for (Olimpiada olimpiada : olimpiadas) {
                oos.writeObject(olimpiada);
            }
        } catch (IOException e) {
            System.out.println("ERROR! NO SE HA PODIDO GUARDAR EL ARCHIVO!");
            e.printStackTrace();
        }
        return true;
    }

    private static ArrayList<Olimpiada> leerOlimpiadas() {
        ArrayList<Olimpiada> olimpiadas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_OLIMPIADAS_BIN))) {
            while (true) {
                try {
                    Olimpiada olimpiada = (Olimpiada) ois.readObject();
                    olimpiadas.add(olimpiada);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR! NO SE HA PODIDO LEER EL ARCHIVO!");
        }
        return olimpiadas;
    }

    public static void main(String[] args) {
        menu();
    }
}
