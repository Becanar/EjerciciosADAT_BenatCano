import java.io.Serializable;

public class Olimpiada implements Serializable {
    private String sede;
    private int anio;
    private String temporada; // "Summer" o "Winter"

    public Olimpiada(String sede, int anio, String temporada) {
        this.sede = sede;
        this.anio = anio;
        this.temporada = temporada;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return "Olimpiada{" +
                "sede='" + sede + '\'' +
                ", anio=" + anio +
                ", temporada='" + temporada + '\'' +
                '}';
    }
}
