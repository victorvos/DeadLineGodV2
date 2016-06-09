package model;

import java.sql.Date;

/**
 * Created by Eigenaar on 31-5-2016.
 */
public class Deadline {
    private String naam;
    private String beschrijving;
    private String URI;
    private Date datum;
    private int ID;
    private String beoordeling;
    private Klas k;

    public Deadline(String naam, Date datum, Klas k) {
        this.naam = naam;
        this.datum = datum;
        this.k = k;
    }


    public String getNaam() {
        return naam;
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

    public Date getDatum() {
        return datum;
    }

    public Klas getK() {
        return k;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBeoordeling() {
        return beoordeling;
    }

    public void setBeoordeling(String beoordeling) {
        this.beoordeling = beoordeling;
    }


}
