package model;

import java.util.ArrayList;

/**
 * Created by Eigenaar on 31-5-2016.
 */
public class Klas {
    private String klasCode;

    public void setKlasCode(String klasCode) {
        this.klasCode = klasCode;
    }

    public Klas(String kc){
        klasCode = kc;
    }

    public String getKlasCode() {
        return klasCode;
    }
}
