package org.example.ejerciciosFichaUd1;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio1 {
    private static void guardarDepartamentos(String dir, ArrayList<Departamento> departamentos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir))) {
            oos.writeObject(departamentos);
        } catch (IOException e) {
            System.out.println("Error! Error en la entrada/salida");
        }
    }
    private static void verDepartamentos(String dir){

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir))){

            ArrayList<Departamento> departamentos= (ArrayList<Departamento>) ois.readObject();
            for(Departamento dp:departamentos){
                System.out.println(dp);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error! Error en la entrada/salida de datos");
        }
    }
    public static void main(String[] args) {
        String dir="src/main/archivos/Departamentos.dat";
        /*ArrayList<Departamento> lstDep=new ArrayList<Departamento>();
        lstDep.add(new Departamento(1,"Contabilidad","Agurain"));
        lstDep.add(new Departamento(2,"Informatica","Vitoria"));
        lstDep.add(new Departamento(3,"Administracion","Agurain"));
        guardarDepartamentos(dir,lstDep);*/
        verDepartamentos(dir);
    }


}
