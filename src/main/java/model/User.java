package model;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Eigenaar on 2-5-2016.
 */
public class User {

    private String userID;
    private String password;
    private String email;
    private String naam;
    private String achternaam;
    private String tussenvoegsel;
    private int isDocent;
    private Klas k;

    public User(String password, String email, String naam, String achternaam, int isDocent, Klas k) {
        this.password = password;
        this.email = email;
        this.naam = naam;
        this.isDocent = isDocent;
        this.k = k;
        this.achternaam = achternaam;
    }

    public int isDocent() {
        return isDocent;
    }

    public Klas getK() {
        return k;
    }


    public String getUserID() {
        return userID;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getName() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){ return password; }

    public int getIsDocent() {
        return isDocent;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getNaam() {
        return naam;
    }

    public String getAchternaam() {
        return achternaam;
    }
}

