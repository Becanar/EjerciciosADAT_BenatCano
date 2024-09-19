package org.example.ejerciciosFichaUd1;

import java.io.*;

public class Actividad4 {
    private static boolean actualizarSalarioEmpleado(String fich, int id, double incremento) throws IOException {
        File fichero = new File(fich);
        RandomAccessFile file = new RandomAccessFile(fichero, "rw"); // "rw" para poder leer y escribir
        int posicion = 0;
        double salario = -1;
        char[] apellido = new char[10];
        char aux;
        while (posicion < file.length()) {
            file.seek(posicion);
            int idf = file.readInt();
            if (id == idf) {
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido).trim();

                file.readInt();
                salario = file.readDouble();

                // Mostrar el apellido y salario antiguo
                System.out.println("Empleado encontrado:");
                System.out.println("Apellido: " + apellidos + ", Salario antiguo: " + salario);

                // Actualizar el salario sumando el incremento
                double nuevoSalario = salario + incremento;

                // Volver a la posición del salario para actualizarlo
                file.seek(posicion + 4 + 20 + 4); // Saltar ID, Apellido y Departamento
                file.writeDouble(nuevoSalario); // Escribir el nuevo salario

                System.out.println("Nuevo salario: " + nuevoSalario);
                file.close();
                return true;
            }
            posicion += 36;
        }
        file.close();
        System.out.println("Error! El empleado no existe!");
        return false;
    }

    public static void main(String[] args) throws IOException {
        String fich = "src/main/archivos/AleatorioEmple.dat";
        actualizarSalarioEmpleado(fich, Integer.parseInt(args[0]), Double.parseDouble(args[1]));
    }
}

