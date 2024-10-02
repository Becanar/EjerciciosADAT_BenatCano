import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorOlimpiadas {

    private static final String RUTA_ATHLETES="archivos/athlete_events.csv";
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
        try(BufferedReader br=new BufferedReader(new FileReader(athletes));BufferedWriter bw=new BufferedWriter(new FileWriter(RUTA_OLIMPIADAS))) {
        String linea=br.readLine();
        while(linea!=null){
            String[] lineas=linea.split(",");
            String row=lineas[8]+","+lineas[9]+";"+lineas[10]+";"+lineas[11];
            bw.write(row);
            bw.newLine();
            linea=br.readLine();
        }
        } catch (IOException e) {
            System.out.println("ERROR! ERROR EN LA ENTRADA/SALIDA DE DATOS!");
            return false;
        }
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
                String[] lineas=linea.split(",");
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
                String[] lineas=linea.split(",");
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
            String deportista = "\"" + id + "\",\"" + nombre + "\",\"" + sexo + "\","
                     + edad + "," + altura + "," + peso + ",\""
                    + equipo + "\",\"" + noc + "\",\"" + games + "\",\"" + anio + ",\"" + temporada + "\",\""+city+"\",\""+deporte+"\",\""+evento+"\",\""+medalla+"\"";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ATHLETES, true))) {
                bw.write(deportista);
                bw.newLine();
                System.out.println("Deportista añadido correctamente.");
            } catch (IOException e) {
                System.out.println("Error al añadir deportista: " + e.getMessage());
            }
            return true;
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
                String[] lineas = linea.split(",");
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
}