package org.example.ejerciciosFichaUd1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio4 {
    public static void copiarFichero(String fchO, String fchD) {
        try (FileInputStream fis = new FileInputStream(fchO);
             FileOutputStream fos = new FileOutputStream(fchD)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }

            System.out.println("Copia completada exitosamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la entrada/salida de datos - " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Hay que pasar dos parámetros: <ficheroOrigen> <ficheroDestino>");
            return;
        }
        String fchO = args[0];
        String fchD = args[1];
        copiarFichero(fchO, fchD);
    }
}
