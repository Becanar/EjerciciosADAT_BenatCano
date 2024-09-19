package org.example.ejerciciosFichaUd1;

import java.io.Serializable;

public class Departamento implements Serializable {
    private int numDep;
    private String nombre,localidad;
    Departamento(int d,String n,String l){
        this.numDep=d;
        this.nombre=n;
        this.localidad=l;
    }

    public int getNumDep() {
        return numDep;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento de "+nombre+". Num dep:"+numDep+". Localizado en: "+localidad+".";
    }
}
