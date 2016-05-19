import java.util.ArrayList;

/**
 * Created by Victor on 19-5-2016.
 */
public class CountryDAO {
    private Country country;
    private ArrayList<Country> countryList = new ArrayList<Country>();

    public Country save(Country country){
        if (!countryList.contains(country)){
            countryList.add(country);
        }
        return country;
    }

    public ArrayList<Country> findAll(){
        return countryList;
    }



}
