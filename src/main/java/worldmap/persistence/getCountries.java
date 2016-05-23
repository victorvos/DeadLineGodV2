package worldmap.persistence;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 23-5-2016.
 */
@Path("/countries")
public class getCountries {
    CountryService worldService = ServiceProvider.getWorldService();
    List<Country> countries = worldService.getAllCountries();
    JsonArrayBuilder jab = Json.createArrayBuilder();

    public JsonArrayBuilder fillArray(List<Country> countries) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Country c : countries) {
            JsonObjectBuilder job = Json.createObjectBuilder();

            job.add("code", c.getCode());
            job.add("name", c.getName());
            job.add("continent", c.getContinent());
            job.add("region", c.getRegion());
            job.add("surface", c.getSurface());
            job.add("population", c.getPopulation());
            job.add("government", c.getGovernmentForm());

            jab.add(job);
        }
        return jab;
    }

    @GET
    @Produces("application/json")
    public String getCountries() {
        List<Country> c = worldService.getAllCountries();
        JsonObjectBuilder job = Json.createObjectBuilder();
        fillArray(c);
        jab.add(job);

        JsonArray array = jab.build();
        return array.toString();
    }

//    @GET
//    @Path("restservices/countries")
//    @Produces("application/json")
//    public String getCountryById(@QueryParam("code") String code) {
//        ArrayList<Country> c = new ArrayList<Country>();
//        c.add(worldService.getCountryByCode(code));
//
//        JsonArray json = fillArray(c).build();
//        System.out.println(json.toString());
//
//        return json.toString();
//    }

    @GET
    @Path("/largestsurfaces")
    @Produces("application/json")
    public String getLargestSurfaces() {
        List<Country> countries = worldService.get10LargestSurfaces();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        fillArray(countries);

        JsonArray json = jab.build();
        System.out.println(json.toString());

        return json.toString();
    }

    @GET
    @Path("/largestpopulations")
    @Produces("application/json")
    public String getLargestPopulations() {
        List<Country> countries = worldService.get10LargestPopulations();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        fillArray(countries);

        JsonArray json = jab.build();
        System.out.println(json.toString());

        return json.toString();
    }
}