package ar.com.gestor.stats.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.gestor.stats.exceptions.NeightBirdHoodExpection;
import ar.com.gestor.stats.model.City;
import ar.com.gestor.stats.model.LocalityStats;
import ar.com.gestor.stats.util.FilterUtil;
import ar.com.gestor.stats.util.GraphCityUtil;

@Service("Service")
public class StatsService implements IStats  {
	
	@Qualifier("cities")
	@Autowired
	private List<City> cities;
	private Map<String,City> cityByName;
	
	@PostConstruct
	public void init() {
		cityByName = cities
						.stream()
						.collect(Collectors.toMap(City::getName, city -> city));
	}

	@Override
	public List<City> getNeighBirdHodOfLocaly(String name) {

		Map<String,List<City>> map = GraphCityUtil.listToGraph(cities);
		List<City> locality = map.get(name);
		return locality;
	}

	@Override
	public List<City> getTopLocality() throws NeightBirdHoodExpection{
		Map<String,List<City>> map = GraphCityUtil.listToGraph(cities);
		if (map.isEmpty()) throw new NeightBirdHoodExpection("There is no city with neightBidrHood");
		return this.getTopLocality(map);
		
	}
	
	@Override
	public List<City> getFurtherAwayLocations() {	
		List<City> futherAwaysCities = FilterUtil.getFutherAwayCities(cities);
		return FilterUtil.filterByLess50km(futherAwaysCities);
	}
	
	

	private List<City> getTopLocality(Map<String,List<City>> map) {
		List<LocalityStats> list = new LinkedList<>();
		for(Entry<String, List<City>> locality:map.entrySet()) {
			list.add(new LocalityStats(locality.getKey(),locality.getValue().size()));		
		}

		list.sort(new Comparator<LocalityStats>() {
			@Override
			public int compare(LocalityStats o1, LocalityStats o2) {
				return o1.getNeightBirdHoods() - o2.getNeightBirdHoods();
			}
		});
		
		int totalNeighBirdHoods = list.get(0).getNeightBirdHoods();
		
		return list.stream()
		.filter(locality->locality.getNeightBirdHoods() == totalNeighBirdHoods)
		.map(locality->locality.getName())
		.map(name-> cityByName.get(name))
		.collect(Collectors.toList());
	}

	
	
	
	

	
	
	

}
