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
    private String tussenvoegsel;
    private int isDocent;
    private Klas k;

    public User(String password, String email, String naam, String tussenvoegsel, int isDocent, Klas k) {
        this.password = password;
        this.email = email;
        this.naam = naam;
        this.tussenvoegsel = tussenvoegsel;
        this.isDocent = isDocent;
        this.k = k;
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

    public boolean checkPassword(String pw) {
        boolean checkpw = false;
        if (pw.equals(password)) {
            checkpw = true;
        }
        return checkpw;
    }

}

