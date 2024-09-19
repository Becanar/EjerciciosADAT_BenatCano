package org.example.ejerciciosFichaUd1;

import java.io.*;

public class AyudaEmpleados {
    private static void crearFichero() throws IOException {
        File fichero = new File("src/main/archivos/AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        String[] apellido = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY"};

        int[] dep = {10, 20, 10, 10, 30, 30, 20};
        Double[] salario = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0};
        StringBuffer buffer = null;
        int n = apellido.length;
        for (int i = 0; i < n; i++) {
            file.writeInt(i + 1); //uso i+1 para identificar empleado
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10); //10 caracteres para el apellido
            file.writeChars(buffer.toString()); //insertar apellido
            file.writeInt(dep[i]); //insertar departamento
            file.writeDouble(salario[i]); //insertar salario
        }
        file.close(); //cerrar fichero
    }

    private static void leerFichero() throws IOException {
        File fichero = new File("src/main/archivos/AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int id, dep, posicion;
        double salario;
        char[] apellido = new char[10];
        char aux;
        posicion = 0;
        for (; ; ) {
            file.seek(posicion);
            id = file.readInt();
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();
                apellido[i] = aux;
            }
            String apellidos = new String(apellido).trim();
            dep = file.readInt();
            salario = file.readDouble();
            System.out.println("ID: " + id + ", Apellido: " + apellidos + ", Departamento: " + dep + ", Salario: " + salario);
            posicion = posicion + 36;

            if (file.getFilePointer() == file.length()) break;
        }
        file.close();
    }

    public static void main(String[] args) throws IOException {
        //crearFichero();
        leerFichero();
    }
}

