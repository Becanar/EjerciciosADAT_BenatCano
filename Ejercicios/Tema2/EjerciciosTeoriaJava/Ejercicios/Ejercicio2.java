package org.example.ejerciciosFichaUd1;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio2
{
    private static boolean modificarDepartamento(String dir,int numDep,String nom,String loc){

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir))){
            Departamento depV=null,depN = null;
            ArrayList<Departamento> departamentos= (ArrayList<Departamento>) ois.readObject();
            for(Departamento dp:departamentos){
                if (dp.getNumDep()==numDep){
                    depV=new Departamento(dp.getNumDep(),dp.getNombre(), dp.getLocalidad());
                    depN=dp;
                    dp.setNombre(nom);
                    dp.setLocalidad(loc);
                }
            }
            if(depV==null){
                System.out.println("El departamento no existe!");
                return false;
            }else{
                System.out.println("Estos son los ANTIGUOS datos del departamento: ");
                System.out.println(depV);
                System.out.println("Estos son los NUEVOS datos del departamento: ");
                System.out.println(depN);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir))) {
                    oos.writeObject(departamentos);
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
        //modificarDepartamento(dir,Integer.parseInt(args[0]),args[1],args[2]);
    }
}
