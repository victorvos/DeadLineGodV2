package worldmap.persistence;
import com.sun.jndi.cosnaming.IiopUrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Victor on 19-5-2016.
 */
public class CountryDAO extends BaseDAO {
    private Country country;
    private ArrayList<Country> countryList = new ArrayList<Country>();

    private List<Country> selectCountries(String query) {
        List<Country> results = new ArrayList<Country>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                String code = dbResultSet.getString("code");
                String name = dbResultSet.getString("name");
                String continent = dbResultSet.getString("continent");
                String region = dbResultSet.getString("region");
                int population = dbResultSet.getInt("population");
                double surface = dbResultSet.getDouble("surface");
                String governmentForm = dbResultSet.getString("governmentForm");

                Country country = new Country(code,name,continent,region,surface,population,governmentForm);
                results.add(country);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return results;
    }


    public Country save(Country c) {
        try (Connection con = super.getConnection()) {

            String code = c.getCode();
            String name = c.getName();
            String continent = c.getContinent();
            String region = c.getRegion();
            int population = c.getPopulation();
            double surface = c.getSurface();
            String governmentForm = c.getGovernmentForm();

            Statement statement = con.createStatement();
            statement.executeQuery("INSERT INTO countries (code,name,continent,region,population,surface,governmentForm) VALUES(" + code + "," + name + "," + continent + "," + region + "," + population + "," + surface + "," + governmentForm + ")");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return country;
    }

    public List<Country> findAll(){
        return selectCountries("select * from country");
    }

    public List<Country> get10LargestPopulations() {
        return selectCountries("select * from country order by population desc limit 10");
    }

    public List<Country> get10LargestSurfaces() {
        return selectCountries("select * from country order by surfaces desc limit 10");
    }

    public Country findByCode(String code) {
        return selectCountries("select * from country where code = "+ code+ "").get(0);
    }

    public Country update(Country country) {

        try(Connection con = super.getConnection()) {

            String code = country.getCode();
            String name = country.getName();
            String continent = country.getContinent();
            String region = country.getRegion();
            int population = country.getPopulation();
            double surface = country.getSurface();
            String governmentForm = country.getGovernmentForm();

            Statement statement = con.createStatement();
            statement.executeQuery("UPDATE country SET name="+name+", continent="+continent+", region="+region+", population="+population+", surface="+surface+", governmentForm="+governmentForm+" WHERE code="+code);

        }catch (SQLException sqle) { sqle.printStackTrace(); }

        return country;

    }

    public boolean delete(Country country) {

        try(Connection con = super.getConnection()) {

            Statement stmt = con.createStatement();
            if(stmt.executeQuery("DELETE FROM country WHERE code=" + country.getCode()) != null) {
                return true;
            }

        }catch (SQLException sqle) { sqle.printStackTrace(); }
        return false;

    }



}
