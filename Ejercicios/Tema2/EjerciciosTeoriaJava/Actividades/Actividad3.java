package org.example.ejerciciosFichaUd1;
import java.io.*;
public class Actividad3 {
    private static boolean buscarEmpleado(String fich,int id) throws IOException {
        File fichero = new File(fich);
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int dep, posicion=0;
        double salario = -1;
        char[] apellido = new char[10];
        char aux;

        while (posicion < file.length()-1) {
            file.seek(posicion);
            int idf = file.readInt();
            if (id == idf) {

                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido).trim();
                dep = file.readInt();
                salario = file.readDouble();

                System.out.println("ID: " + id + ", Apellido: " + apellidos + ", Departamento: " + dep + ", Salario: " + salario);
                file.close();
                return true;
            }

            posicion = posicion + 36; // siguiente posición (ID + Apellido + Dep + Salario)
        }

        file.close();

        System.out.println("Error! El empleado no existe!");
        return false;
    }
    public static void main(String[] args) throws IOException {
        String fich="src/main/archivos/AleatorioEmple.dat";
        buscarEmpleado(fich,Integer.parseInt(args[0]));
    }
}
