package worldmap.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryService {
	private List<Country> allCountries = new ArrayList<Country>();
	
	public CountryService() {
		allCountries.add(new Country("CHN", "China", "Asia", "Eastern Asia", 9572900.00, 1277558000, "People'sRepublic"));
		allCountries.add(new Country("IND", "India", "Asia", "Southern and Central Asia", 3287263.00, 1013662000, "Federal Republic"));
		allCountries.add(new Country("USA", "United States", "North America", "North America", 9363520.00, 278357000, "Federal Republic"));
		allCountries.add(new Country("IDN", "Indonesia", "Asia", "Southeast Asia", 1904569.00, 212107000, "Republic"));
		allCountries.add(new Country("BRA", "Brazil", "South America", "South America", 8547403.00, 170115000, "Federal Republic"));
		allCountries.add(new Country("PAK", "Pakistan", "Asia", "Southern and Central Asia", 796095.00, 156483000, "Republic"));
		allCountries.add(new Country("RUS", "Russian Federation", "Europe", "Eastern Europe", 17075400.00, 146934000, "Federal Republic"));
		allCountries.add(new Country("BGD", "Bangladesh", "Asia", "Southern and Central Asia", 143998.00, 129155000, "Republic"));
		allCountries.add(new Country("JPN", "Japan", "Asia", "Eastern Asia", 377829.00, 126714000, "Constitutional Monarchy"));
		allCountries.add(new Country("NGA", "Nigeria", "Africa", "Western Africa", 923768.00, 111506000, "Federal Republic"));
		allCountries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 13120000.00, 0, "Co-administrated"));
		allCountries.add(new Country("CAN", "Canada", "North America", "North America", 9970610.00, 31147000, "Constitutional Monarchy, Federation"));
		allCountries.add(new Country("AUS", "Australia", "Oceania", "Australia and New Zealand", 7741220.00, 18886000, "Constitutional Monarchy, Federation"));
		allCountries.add(new Country("ARG", "Argentina", "South America", "South America", 2780400.00, 37032000, "Federal Republic"));
		allCountries.add(new Country("KAZ", "Kazakstan", "Asia", "Southern and Central Asia", 2724900.00, 16223000, "Republic"));
	}
	
	public List<Country> getAllCountries() {
		return allCountries;
	}
	
	public List<Country> get10LargestPopulations() {
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});
		
		return allCountries.subList(0, 10);
	}

	public List<Country> get10LargestSurfaces() {
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			};
		});
		
		return allCountries.subList(0, 10);
	}
	
	public Country getCountryByCode(String code) {
		Country result = null;
		
		for (Country c : allCountries) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		
		return result;
	}
}
