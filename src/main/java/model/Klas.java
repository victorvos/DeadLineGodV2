package model;

import java.util.ArrayList;

/**
 * Created by Eigenaar on 31-5-2016.
 */
public class Klas {
    private String klasCode;
    ArrayList<Deadline> deadlines = new ArrayList<Deadline>();

    public Klas(String kc){
        klasCode = kc;
    }
}
