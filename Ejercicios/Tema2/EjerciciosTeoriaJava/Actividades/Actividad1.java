package org.example.ejerciciosFichaUd1;

import java.io.File;

public class Actividad1 {
    public static void main(String[] args) {
        String dir=args[0];
        System.out.println("Estos son los archivos en el directorio: "+dir);
        File f=new File(dir);

        if (!f.exists() || !f.isDirectory()) {
            System.out.println("El directorio especificado no existe o no es un directorio válido.");
            return;
        }
        String[] listaArchivos = f.list();
        if (listaArchivos != null && listaArchivos.length > 0) {
            System.out.println("Archivos en el directorio " + dir + ":");
            for (String archivo : listaArchivos) {
                System.out.println(archivo);
            }
        } else {
            System.out.println("El directorio está vacío.");
        }
    }
}


