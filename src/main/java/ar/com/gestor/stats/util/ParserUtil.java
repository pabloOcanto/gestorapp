package ar.com.gestor.stats.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.gestor.stats.model.City;

public class ParserUtil {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	public static City parser(File file) throws JsonParseException, JsonMappingException, IOException {
		City city = mapper.readValue(file, City.class);
		return city;	 
	}

}
