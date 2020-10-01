package ar.com.gestor.stats.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.com.gestor.stats.model.City;

@RunWith(JUnit4.class)
public class FilterUtilTest {

	List<City> cities = new ArrayList<>();
	
	@Before
	public void loadFile() throws JsonParseException, JsonMappingException, IOException {
		ClassLoader cl = getClass().getClassLoader();
		for(int i=0;i<8;i++) {
			File file = ResourceUtils.getFile(cl.getResource("input/"+i+".json").getFile());
			cities.add(ParserUtil.parser(file));		
		}

	}
	
	@Test	
	public void getLocalities() {	
			List<City> citiesFiltered = FilterUtil.fitlerByLocality(cities);	
			assertEquals(3, citiesFiltered.size());
	}
	
	@Test	
	public void getMiansLocality() {	
			List<City> citiesFiltered = FilterUtil.fitlerByLocality(cities);	
			citiesFiltered = FilterUtil.isLocality(citiesFiltered);
			assertEquals(1, citiesFiltered.size());
			assertEquals("Manhattan", citiesFiltered.get(0).getName());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void getFutherAwayCities() {
		List<City> citiesFiltered = FilterUtil.getFutherAwayCities(cities);	
		assertEquals(2, citiesFiltered.size());
		equals(citiesFiltered.get(0).getName().equals("Palermo"));
	}
	
	@Test
	public void getLocationsFutherAway50km() {
		List<City> citiesFiltered = FilterUtil.getFutherAwayCities(cities);	
		citiesFiltered = FilterUtil.filterByLess50km(citiesFiltered);
		assertEquals(2, citiesFiltered.size());
	}
	
	
	
	
}
