package ar.com.gestor.stats.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.com.gestor.stats.model.City;

public class GraphCityUtil {
	
	private static Map<String,List<City>> neightBirdHoods= new HashMap<String, List<City>>();
	
	public static Map<String,List<City>> listToGraph(List<City> list) {
		List<City> localities = FilterUtil.fitlerByLocality(list);	
		List<City> mainLocalities = FilterUtil.isLocality(localities);
		
		for (City city:mainLocalities) { 
			
			List<City> localitiesList = localities
					.stream()
					.filter(locality->!locality.equals(city))
					.collect(Collectors.toList());
					neightBirdHoods.put(city.getName(), localitiesList);
		}
		
		return neightBirdHoods;
	}

}
