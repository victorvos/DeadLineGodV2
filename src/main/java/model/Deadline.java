package model;

/**
 * Created by Eigenaar on 31-5-2016.
 */
public class Deadline {
    private String naam;
    private String beschrijving;
    private String URI;
    private String datum;
    private int ID;
    private int beoordeling;
    private Klas k;

    public Deadline(String naam, String datum, Klas k) {
        this.naam = naam;
        this.datum = datum;
        this.k = k;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getDatum() {
        return datum;
    }

    public Klas getK() {
        return k;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBeoordeling() {
        return beoordeling;
    }

    public void setBeoordeling(int beoordeling) {
        this.beoordeling = beoordeling;
    }


}
