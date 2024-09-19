package org.example.ejerciciosFichaUd1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Actividad2 {
    private static void verFich(String dir){
        try {
            BufferedReader br=new BufferedReader(new FileReader(dir));
            String linea= br.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea=br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! Fichero no existe!");
        } catch (IOException e) {
            System.out.println("Error! Error en la entrada/salida");
        }

    }
    public static void main(String[] args) {
        String dir=args[0];
        verFich(dir);
    }
}
