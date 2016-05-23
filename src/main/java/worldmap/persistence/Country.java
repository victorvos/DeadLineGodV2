package worldmap.persistence;

/**
 * Created by Victor on 19-5-2016.
 */
public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private double surface;
    private int population;
    private String governmentForm;

    public Country(String code, String name, String continent, String region, double surface, int population, String governmentForm) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surface = surface;
        this.population = population;
        this.governmentForm = governmentForm;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public String getCode() {
        return code;
    }

    public int getPopulation() {
        return population;
    }

    public double getSurface() {
        return surface;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }
}
