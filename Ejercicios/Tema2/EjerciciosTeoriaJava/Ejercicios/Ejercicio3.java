package org.example.ejerciciosFichaUd1;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio3 {
    private static boolean eliminarDepartamento(String dir,int numDep){

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir))){
            ArrayList<Departamento> departamentos= (ArrayList<Departamento>) ois.readObject();
            ArrayList<Departamento> departamentos2=new ArrayList<Departamento>();
            for(Departamento dp:departamentos){
                if (dp.getNumDep()!=numDep){
                    departamentos2.add(dp);
                }
            }
            if(departamentos2.size()==departamentos.size()){
                System.out.println("El departamento no existe!");
                return false;
            }else{
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir))) {
                    oos.writeObject(departamentos2);
                    System.out.println("Ahora hay "+departamentos2.size()+" departamentos.");
                    return true;
                } catch (IOException e) {
                    System.out.println("Error! No se pudo guardar los cambios en el archivo.");
                    return false;
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error! Error en la entrada/salida de datos");
            return false;
        }

    }
    public static void main(String[] args) {
        String dir="src/main/archivos/Departamentos.dat";
       //eliminarDepartamento(dir, Integer.parseInt(args[0]));
    }
}
