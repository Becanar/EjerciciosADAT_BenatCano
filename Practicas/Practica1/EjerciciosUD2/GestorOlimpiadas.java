/*import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorOlimpiadas {

    private static final String RUTA_ATHLETES="archivos/athlete_events_corregido.csv";
    private static final String RUTA_OLIMPIADAS="archivos/olimpiadas.csv";
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        sc=new Scanner(System.in);
        boolean seguir=true;
        while(seguir){
            System.out.println("------MENÚ OLIMPIADAS------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear olimpiadas.csv");
            System.out.println("2.Buscar un atleta (Por nombre)");
            System.out.println("3.Buscar un atleta (Por deporte/año)");
            System.out.println("4.Añadir atleta");
            System.out.println("5.Salir");
            try {
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion){
                    case 1:{generarOlimpiadas();break;}
                    case 2:{buscarAtleta();break;}
                    case 3:{buscarAtletaDeporteOlimpiada();break;}
                    case 4:{aniadirAtleta();break;}
                    case 5:{seguir=false;break;}
                    default:{System.out.println("ERROR! OPCIÓN NO VÁLIDA!");break;}
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN!");
                sc.nextLine();
            }
        }
        sc.close();
    }


    //Metodo 1
    private static boolean generarOlimpiadas() {
        File athletes=new File(RUTA_ATHLETES);
        if(!athletes.exists()){
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }
        if(!athletes.isFile()){
            System.out.println("ERROR! EL OBJETO ORIGINAL NO ES UN FICHERO!");
            return false;
        }
        ArrayList<String> olimpiadas=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(athletes))) {
        String linea=br.readLine();
        while(linea!=null){
            String[] lineas=linea.split(";");
            String row=lineas[8]+","+lineas[9]+","+lineas[10]+","+lineas[11];
            boolean existe=false;
            for(String str: olimpiadas){
                if(str.equalsIgnoreCase(row)){
                    existe=true;
                }
            }
            if(!existe){
                olimpiadas.add(row);
            }
            System.out.println(olimpiadas.size());
            linea=br.readLine();
        }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(RUTA_OLIMPIADAS))){
            int cont=olimpiadas.size();
            for(String str: olimpiadas){
                bw.write(str);
                cont--;
                if(cont>0){
                bw.newLine();}
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        System.out.println("FICHERO CREADO CORRECTAMENTE");
        return true;
    }
    //Metodo2
    private static boolean buscarAtleta(){
        System.out.println("¿Qué atleta desea buscar?");
        String nombreIntroducido=sc.nextLine();
        File athletes=new File(RUTA_ATHLETES);
        if(!athletes.exists()){
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }
        if(!athletes.isFile()){
            System.out.println("ERROR! EL OBJETO ORIGINAL NO ES UN FICHERO!");
            return false;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(athletes));) {
            String linea=br.readLine();
            boolean existe=false;
            while(linea!=null){
                String[] lineas=linea.split(";");
                if(lineas[1].equalsIgnoreCase("\""+nombreIntroducido+"\"")){
                    System.out.println("Deportista: " + lineas[1] + " | Sexo: " + lineas[2] + " | Edad: " + lineas[3]
                            + " | Equipo: " + lineas[6] + " | Juegos: " + lineas[8] + " | Evento: " + lineas[13] + " | Medalla: " + lineas[14]);
                    existe=true;
                }
                linea=br.readLine();
            }
            if(!existe){
                System.out.println("NO EXISTE DICHO ATLETA!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        return true;
    }
    //Metodo 3

    private static boolean buscarAtletaDeporteOlimpiada() {
        System.out.println("¿En qué deporte desea buscar?");
        String deporte=sc.nextLine();
        System.out.println("¿En qué año desea buscar?");
        String anio=sc.nextLine();
        System.out.println("¿En qué temporada desea buscar?");
        String temporada=sc.nextLine();
        File athletes=new File(RUTA_ATHLETES);
        if(!athletes.exists()){
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }
        if(!athletes.isFile()){
            System.out.println("ERROR! EL OBJETO ORIGINAL NO ES UN FICHERO!");
            return false;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(athletes));) {
            String linea=br.readLine();
            boolean existe=false;
            while(linea!=null){
                String[] lineas=linea.split(";");
                if(lineas[9].equalsIgnoreCase(anio)&&lineas[10].equalsIgnoreCase("\""+temporada+"\"")&&lineas[12].equalsIgnoreCase("\""+deporte+"\"")){
                    if(!existe){
                        System.out.println("Olimpiadas: " + lineas[8] + " | Ciudad: " + lineas[11] + " | Deporte: " + lineas[12]+": ");
                    }
                    existe=true;
                    System.out.println("Deportista: " + lineas[1] + " | Evento: " + lineas[13] + " | Medalla: " + lineas[14]);
                }
                linea=br.readLine();
            }
            if(!existe){
                System.out.println("NO EXISTE DICHO ATLETA!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        return true;
    }

    private static boolean aniadirAtleta() {

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nombre del deportista: ");
            String nombre = sc.nextLine();
            System.out.print("Introduzca el sexo (M/F): ");
            String sexo = sc.nextLine();
            System.out.print("Introduzca la edad: ");
            String edad = sc.nextLine();
            System.out.print("Introduzca la altura (cm/NA): ");
            String altura = sc.nextLine();
            System.out.print("Introduzca el peso (kg/NA): ");
            String peso = sc.nextLine();
            System.out.print("Introduzca el equipo: ");
            String equipo = sc.nextLine();
            System.out.print("Introduzca el NOC: ");
            String noc = sc.nextLine();
            System.out.print("Introduzca el año de la Olimpiada: ");
            String anio = sc.nextLine();
            System.out.print("Introduzca la temporada (Summer/Winter): ");
            String temporada = sc.nextLine();
            System.out.print("Introduzca la ciudad: ");
            String city = sc.nextLine();
            System.out.print("Introduzca el deporte: ");
            String deporte = sc.nextLine();
            System.out.print("Introduzca el evento: ");
            String evento = sc.nextLine();
            System.out.print("Introduzca la medalla (Gold, Silver, Bronze o NA): ");
            String medalla = sc.nextLine();


            String id = generarNuevoId(nombre,sexo,equipo,evento,anio,temporada);
            if (id == null) {
                System.out.println("ERROR!NO SE HA PODIDO GENERAR ID.");
                return false;
            }
            String games=anio+" "+temporada;
            games.replaceAll("\"","");
            String deportista = "\"" + id + "\";\"" + nombre + "\";\"" + sexo + "\";"
                     + edad + ";" + altura + ";" + peso + ";\""
                    + equipo + "\";\"" + noc + "\";\"" + games + "\";\"" + anio + ";\"" + temporada + "\";\""+city+"\";\""+deporte+"\";\""+evento+"\";\""+medalla+"\"";
            String[] depor=deportista.split(";");
            if(depor.length==15){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ATHLETES, true))) {
                bw.write(deportista);
                bw.newLine();
                System.out.println("DEPORTISTA AÑADIDO CORRECTAMENTE");
                return true;
            } catch (IOException e) {
                System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
                return false;
            }
            }
            else {
                System.out.println("ERROR! FALTAN DATOS PARA AÑADIR DEPORTISTA");
                return false;
            }
    }

    private static String generarNuevoId(String nombre, String sexo, String equipo, String evento, String anio, String temporada) {
        File athletes = new File(RUTA_ATHLETES);
        if (!athletes.exists() || !athletes.isFile()) {
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return null;
        }

        String idAux = "0";
        boolean existe = false;

        try (BufferedReader br = new BufferedReader(new FileReader(athletes))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] lineas = linea.split(";");
                String idActual = lineas[0];

                if (lineas[1].equalsIgnoreCase("\""+ nombre +"\"") &&
                        lineas[2].equalsIgnoreCase("\""+sexo+"\"") &&
                        lineas[6].equalsIgnoreCase("\""+equipo+"\"") &&
                        lineas[8].equals(anio) &&
                        lineas[9].equalsIgnoreCase("\""+temporada+"\"") &&
                        lineas[13].equalsIgnoreCase("\""+evento+"\"")
                       ) {

                    System.out.println("ERROR! YA EXISTE UNA LÍNEA EXACTAMENTE IGUAL!");
                    idAux=null;
                    break;
                }
                else{
                    if(lineas[1].equalsIgnoreCase(nombre)) {
                        idAux = idActual;
                        existe=true;
                        break;
                    }
                }
                idAux = idActual;
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return null;
        }

        if (!existe && idAux!=null) {
            try {
                int nuevoId = Integer.parseInt(idAux.replaceAll("\"","")) + 1;
                return ""+ nuevoId ;
            } catch (NumberFormatException e) {
                System.out.println("ERROR! NO SE PUDO GENERAR UN NUEVO ID!");
                return null;
            }
        }
        return idAux;
    }


    public static void main(String[] args) {
        menu();}
}*/
import java.io.*;
import java.util.*;

public class GestorOlimpiadas {

    private static final String RUTA_ATHLETES = "archivos/athlete_events_corregido.csv";
    private static final String RUTA_OLIMPIADAS = "archivos/olimpiadas.csv";
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        boolean seguir = true;
        while (seguir) {
            System.out.println("------MENÚ OLIMPIADAS------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear olimpiadas.csv");
            System.out.println("2.Buscar un atleta (Por nombre)");
            System.out.println("3.Buscar un atleta (Por deporte/año)");
            System.out.println("4.Añadir atleta");
            System.out.println("5.Salir");
            try {
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        generarOlimpiadas();
                        break;
                    case 2:
                        buscarAtleta();
                        break;
                    case 3:
                        buscarAtletaDeporteOlimpiada();
                        break;
                    case 4:
                        aniadirAtleta();
                        break;
                    case 5:
                        seguir = false;
                        break;
                    default:
                        System.out.println("ERROR! OPCIÓN NO VÁLIDA!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN VÁLIDA!");
                sc.nextLine();
            }
        }
        sc.close();
    }

    // Método 1: Generar olimpiadas.csv
    private static boolean generarOlimpiadas() {
        File athletes = new File(RUTA_ATHLETES);
        if (!athletes.exists()) {
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }
        if (!athletes.isFile()) {
            System.out.println("ERROR! EL OBJETO ORIGINAL NO ES UN FICHERO!");
            return false;
        }

        ArrayList<String> olimpiadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(athletes))) {
            String linea = br.readLine();

            while (linea != null) {
                String[] lineas = linea.split(";");

                if (lineas.length > 11) {
                    String anio = lineas[9].replaceAll("\"", "").trim();
                    String temporada = lineas[10].replaceAll("\"", "").trim();
                    String ciudad = lineas[11].replaceAll("\"", "").trim();

                    if (esNumero(anio) && (temporada.equalsIgnoreCase("Summer") || temporada.equalsIgnoreCase("Winter"))) {
                        String row = "\"" + lineas[8] + "\";" + anio + ";\"" + temporada + "\";\"" + ciudad + "\"";

                        if (!olimpiadas.contains(row)) {
                            olimpiadas.add(row);
                        }
                    }
                }
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }

        ordenarOlimpiadas(olimpiadas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_OLIMPIADAS))) {
            for (String str : olimpiadas) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }

        System.out.println("FICHERO CREADO CORRECTAMENTE");
        return true;
    }

    // Método auxiliar para que el año sea numérico
    private static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método auxiliar para que nos ordene las olimpiadas
    private static void ordenarOlimpiadas(ArrayList<String> olimpiadas) {
        Collections.sort(olimpiadas, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] datos1 = o1.split(";");
                String[] datos2 = o2.split(";");
                int anio1 = Integer.parseInt(datos1[1].trim());
                int anio2 = Integer.parseInt(datos2[1].trim());
                String temporada1 = datos1[2].replace("\"", "").trim();
                String temporada2 = datos2[2].replace("\"", "").trim();
                if (anio1 != anio2) {
                    return Integer.compare(anio1, anio2);
                } else {

                    return temporada1.equals("Summer") ? -1 : 1;
                }
            }
        });
    }


    // Método 2: Buscar atleta por nombre
    private static boolean buscarAtleta() {
        System.out.println("¿Qué atleta desea buscar?");
        String nombreIntroducido = sc.nextLine();
        File athletes = new File(RUTA_ATHLETES);

        if (!athletes.exists()) {
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(athletes))) {
            String linea;
            boolean encontrado = false;
            br.readLine();  // Skip header
            while ((linea = br.readLine()) != null) {
                String[] lineas = linea.split(";");
                if (lineas.length >= 15 && lineas[1].replaceAll("\"", "").equalsIgnoreCase(nombreIntroducido)) {
                    System.out.println("Deportista: " + lineas[1] + " | Sexo: " + lineas[2] + " | Edad: " + lineas[3]
                            + " | Equipo: " + lineas[6] + " | Juegos: " + lineas[8] + " | Evento: " + lineas[13] + " | Medalla: " + lineas[14]);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("NO EXISTE DICHO ATLETA!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        return true;
    }

    // Método 3: Buscar atleta por deporte/año/temporada
    private static boolean buscarAtletaDeporteOlimpiada() {
        System.out.println("¿En qué deporte desea buscar?");
        String deporte = sc.nextLine();
        System.out.println("¿En qué año desea buscar?");
        String anio = sc.nextLine();
        System.out.println("¿En qué temporada desea buscar?");
        String temporada = sc.nextLine();

        File athletes = new File(RUTA_ATHLETES);
        if (!athletes.exists()) {
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(athletes))) {
            String linea;
            boolean encontrado = false;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] lineas = linea.split(";");
                if (lineas.length >= 15 && lineas[9].equals(anio) && lineas[10].replaceAll("\"", "").equalsIgnoreCase(temporada)
                        && lineas[12].replaceAll("\"", "").equalsIgnoreCase(deporte)) {
                    if (!encontrado) {
                        System.out.println("Olimpiadas: " + lineas[8] + " | Ciudad: " + lineas[11] + " | Deporte: " + lineas[12]);
                    }
                    encontrado = true;
                    System.out.println("Deportista: " + lineas[1] + " | Evento: " + lineas[13] + " | Medalla: " + lineas[14]);
                }
            }
            if (!encontrado) {
                System.out.println("NO EXISTE NINGÚN ATLETA EN ESE DEPORTE/AÑO!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
        return true;
    }

    // Método 4: Añadir un nuevo atleta
    private static boolean aniadirAtleta() {
        System.out.print("Introduzca el nombre del deportista: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el sexo (M/F): ");
        String sexo = sc.nextLine();
        System.out.print("Introduzca la edad: ");
        String edad = sc.nextLine();
        System.out.print("Introduzca la altura (cm/NA): ");
        String altura = sc.nextLine();
        System.out.print("Introduzca el peso (kg/NA): ");
        String peso = sc.nextLine();
        System.out.print("Introduzca el equipo: ");
        String equipo = sc.nextLine();
        System.out.print("Introduzca el NOC: ");
        String noc = sc.nextLine();
        System.out.print("Introduzca el año de la Olimpiada: ");
        String anio = sc.nextLine();
        System.out.print("Introduzca la temporada (Summer/Winter): ");
        String temporada = sc.nextLine();
        System.out.print("Introduzca la ciudad: ");
        String ciudad = sc.nextLine();
        System.out.print("Introduzca el deporte: ");
        String deporte = sc.nextLine();
        System.out.print("Introduzca el evento: ");
        String evento = sc.nextLine();
        System.out.print("Introduzca la medalla (Gold, Silver, Bronze o NA): ");
        String medalla = sc.nextLine();

        String id = generarNuevoId(nombre, sexo, equipo, evento, anio, temporada);
        if (id == null) {
            System.out.println("ERROR! NO SE HA PODIDO GENERAR ID.");
            return false;
        }

        String games = anio + " " + temporada;
        String nuevoAtleta = "\"" + id + "\";\"" + nombre + "\";\"" + sexo + "\";" + edad + ";" + altura + ";" + peso
                + ";\"" + equipo + "\";\"" + noc + "\";\"" + games + "\";\"" + anio + "\";\"" + temporada + "\";\"" + ciudad
                + "\";\"" + deporte + "\";\"" + evento + "\";\"" + medalla + "\"";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ATHLETES, true))) {
            bw.write(nuevoAtleta);
            bw.newLine();
            System.out.println("DEPORTISTA AÑADIDO CORRECTAMENTE");
            return true;
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
    }

    // Método para generar un nuevo ID
    private static String generarNuevoId(String nombre, String sexo, String equipo, String evento, String anio, String temporada) {
        File athletes = new File(RUTA_ATHLETES);
        if (!athletes.exists() || !athletes.isFile()) {
            System.out.println("ERROR! EL FICHERO ORIGINAL NO EXISTE!");
            return null;
        }

        String idAux = "0";
        try (BufferedReader br = new BufferedReader(new FileReader(athletes))) {
            br.readLine();  // Skip header
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lineas = linea.split(";");
                if (lineas[1].replaceAll("\"", "").equalsIgnoreCase(nombre)
                        && lineas[2].replaceAll("\"", "").equalsIgnoreCase(sexo)
                        && lineas[6].replaceAll("\"", "").equalsIgnoreCase(equipo)
                        && lineas[13].replaceAll("\"", "").equalsIgnoreCase(evento)
                        && lineas[9].equals(anio)
                        && lineas[10].replaceAll("\"", "").equalsIgnoreCase(temporada)) {
                    System.out.println("ERROR! YA EXISTE UNA LÍNEA EXACTAMENTE IGUAL!");
                    return null;
                }
                idAux = lineas[0];  // Update ID
            }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return null;
        }

        try {
            int nuevoId = Integer.parseInt(idAux.replaceAll("\"", "")) + 1;
            return "\"" + nuevoId + "\"";
        } catch (NumberFormatException e) {
            System.out.println("ERROR! NO SE PUDO GENERAR UN NUEVO ID!");
            return null;
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
