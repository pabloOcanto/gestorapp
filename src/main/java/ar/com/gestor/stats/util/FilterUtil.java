package ar.com.gestor.stats.util;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import ar.com.gestor.stats.model.City;
import ar.com.gestor.stats.model.Context;
import ar.com.gestor.stats.model.LocalityStats;

public class FilterUtil {

	public static List<City> fitlerByLocality(List<City> list) {
		return list
				.stream()
				.filter(city -> Optional.ofNullable(city.getType()).orElse("").equals("locality"))
				.collect(Collectors.toList());
	}
	
	public static List<City> isLocality(List<City> list) {
		
		List<City> newList = new ArrayList<>();
		
		for(City city: list) {
			int count =0;
			for(Context context:city.getContext()) {
				if (!context.getType().equals("locality")) {
					count +=1;
				}
			}
			
			if (city.getContext().size() == count) {
				newList.add(city);
			}
		}
		
		return newList;
	}
	
	public static List<City> getFutherAwayCities(List<City> list) {

		
		 Predicate<City> predicate1 =  city -> Optional.ofNullable(city.getType()).orElse("").equals("place");
		 Predicate<City> predicate2 =  city -> Double.valueOf(city.getCenter().split(":")[0]) < 0;
		 Predicate<City> predicate3 =  city -> Double.valueOf(city.getCenter().split(":")[1]) < 0;
		 
		Comparator<City> comparator = (city1, city2) -> {
			Double latLong_1 = Double.valueOf(city1.getCenter().split(":")[0]) + Double.valueOf(city2.getCenter().split(":")[1]);
			Double latLong_2 = Double.valueOf(city1.getCenter().split(":")[0]) + Double.valueOf(city2.getCenter().split(":")[1]);
			return (int) (Math.abs(latLong_1) -Math.abs(latLong_2));
		};

		 
		 return list
			.stream()
			.filter(Objects::nonNull)
			.filter(predicate1.and(predicate2).and(predicate3))
			.sorted(comparator)
			.collect(Collectors.toList());
		
	}
	
	public static List<City> filterByLess50km(List<City> list) {
		
		City furtherAwayCity = list.get(0);
		
		 Predicate<City> distanceFilter =  city -> {
			 Double distance = LocationUtil.distance(furtherAwayCity.getCenter(),city.getCenter());
			 System.out.println(distance.toString());
			 return distance<50;
		 };
			
		return list
			.stream()
			.filter(distanceFilter)
			.collect(Collectors.toList());
				
	}


}
