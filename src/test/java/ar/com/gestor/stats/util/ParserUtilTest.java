package ar.com.gestor.stats.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParseException;
import ar.com.gestor.stats.model.City;
import ar.com.gestor.stats.model.Context;
@RunWith(JUnit4.class)
public class ParserUtilTest {
	
	File file_1,file_2;
	
	@Before
	public void loadFile() throws FileNotFoundException {
		ClassLoader cl = getClass().getClassLoader();
		file_1 = ResourceUtils.getFile(cl.getResource("input/0.json").getFile());
		file_2 = ResourceUtils.getFile(cl.getResource("input/6.json").getFile());
	}
	
	
	@Test
	public void transformJSonToCompleteModel() throws IOException, JsonParseException {
		Context c1 = new Context("place","New York City","40.7648:-73.9808");
		Context c2 = new Context("region","New York","42.751210955:-75.4652471468304");
		Context c3 = new Context("country","United States","39.3812661305678:-97.9222112121185");
		
		List<Context> list = new ArrayList<>();
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		City city = new City("Manhattan","locality","40.7903:-73.9597",list);
		City cityParsed =ParserUtil.parser(file_1);
		
		assertEquals(city, cityParsed);
		
	}
	
	@Test
	public void transformJSonWithOutContext() throws IOException, JsonParseException {

		City city = new City("Palermo","place","-34.58889:-58.43056",null);
		City cityParsed =ParserUtil.parser(file_2);
		assertEquals(city, cityParsed);
		
	}
	

}
