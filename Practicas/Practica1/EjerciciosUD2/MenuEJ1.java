import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class MenuEJ1 {
    private static Scanner sc = new Scanner(System.in);
    private static void menu() {
        sc=new Scanner(System.in);
        boolean seguir=true;
        while(seguir){
            System.out.println("------MENÚ------");
            System.out.println("¿Qué desea hacer?:");
            System.out.println("1.Crear un directorio.");
            System.out.println("2.Listar un directorio.");
            System.out.println("3.Copiar un archivo.");
            System.out.println("4.Mover un archivo.");
            System.out.println("5.Eliminar un archivo/directorio.");
            System.out.println("6.Salir.");
            try {
                int opcion = sc.nextInt();
                switch (opcion){
                    case 1:{crearDirectorio();break;}
                    case 2:{listarDirectorio();break;}
                    case 3:{copiarArchivo();break;}
                    case 4:{moverArchivo();break;}
                    case 5:{eliminarArchivoDirectorio();break;}
                    case 6:{seguir=false;break;}
                    default:{System.out.println("ERROR! OPCIÓN NO VÁLIDA!");break;}
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! INTRODUCE UNA OPCIÓN!");
                sc.nextLine();
            }

        }
        sc.close();
    }
    private static void crearDirectorio() {
        sc=new Scanner(System.in);
        System.out.println("¿Dónde desea crear el directorio?");
        String ruta=sc.nextLine();
        File f = new File(ruta);
        if(f.exists()&&f.isDirectory()){
            System.out.println("¿Qué nombre desea para el directorio?");
            String nombreDir=sc.nextLine();
            ruta=ruta+"/"+nombreDir;
            System.out.println(ruta);
            File dir = new File(f, nombreDir);
            if(dir.mkdir()){
                System.out.println("DIRECTORIO CREADO CORRECTAMENTE!");
            }else{
                System.out.println("ERROR! NO SE HA PODIDO CREAR EL DIRECTORIO!");
            }
        }else {
            System.out.println("ERROR! RUTA NO VÁLIDA!");
        }
    }
    private static void listarDirectorio() {
        sc=new Scanner(System.in);
        System.out.println("¿Qué directorio desea listar?");
        String ruta=sc.nextLine();
        File f = new File(ruta);
        if(f.exists()&&f.isDirectory()){
            String[] arr=f.list();
            if(arr.length==0){
                System.out.println("EL DIRECTORIO ESTÁ VACÍO!");
            }else {
            System.out.println("Contenido del directorio '"+ruta+"':");
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            }}
        }else {
            System.out.println("ERROR! RUTA NO VÁLIDA!");
        }
    }

    private static void copiarArchivo() {
        sc=new Scanner(System.in);
        System.out.println("¿Cuál es la ruta del archivo que desea copiar?");
        String rutaOrigen=sc.nextLine();
        File fOG = new File(rutaOrigen);
        if(fOG.exists()){
            System.out.println("¿Dónde desea copiar el archivo?");
            String nombreArchivo=rutaOrigen.substring(rutaOrigen.lastIndexOf('/'));
            nombreArchivo=nombreArchivo.substring(0,nombreArchivo.indexOf('.'))+"_COPIA.txt";
            String rutaDestino=sc.nextLine()+nombreArchivo;
            Path rO= Paths.get(rutaOrigen);
            Path rD= Paths.get(rutaDestino);
            try {
                Files.copy(rO,rD,REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("ERROR! NO SE HA PODIDO COPIAR EL ARCHIVO!");
            }
        }else {
            System.out.println("ERROR! EL ARCHIVO NO EXISTE!");
        }
    }

    private static void moverArchivo() {
        sc=new Scanner(System.in);
        System.out.println("¿Cuál es la ruta del archivo que desea mover?");
        String rutaOrigen=sc.nextLine();
        File fOG = new File(rutaOrigen);
        if(fOG.exists()){
            System.out.println("¿Dónde desea mover el archivo?");
            String nombreArchivo=rutaOrigen.substring(rutaOrigen.lastIndexOf('/'));
            String rutaDestino=sc.nextLine()+nombreArchivo;
            Path rO= Paths.get(rutaOrigen);
            Path rD= Paths.get(rutaDestino);
            try {
                Files.copy(rO,rD,REPLACE_EXISTING);
                Files.delete(rO);
            } catch (IOException e) {
                System.out.println("ERROR! NO SE HA PODIDO COPIAR EL ARCHIVO!");
            }
        }else {
            System.out.println("ERROR! EL ARCHIVO NO EXISTE!");
        }
    }
    private static void eliminarArchivoDirectorio() {
        sc = new Scanner(System.in);
        System.out.print("Introduce la ruta del archivo o directorio a eliminar: ");
        String ruta = sc.nextLine();

        File archivoOdirectorio = new File(ruta);

        if (archivoOdirectorio.exists()) {
            if (archivoOdirectorio.isDirectory()) {
                if (archivoOdirectorio.list().length > 0) {
                    System.out.println("El directorio no está vacío, no se puede eliminar.");
                } else {
                    archivoOdirectorio.delete();
                    System.out.println("Directorio eliminado correctamente.");
                }
            } else {
                archivoOdirectorio.delete();
                System.out.println("Archivo eliminado correctamente.");
            }
        } else {
            System.out.println("El archivo o directorio no existe.");
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
