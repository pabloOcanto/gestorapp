package ar.com.gestor.stats.service;

import java.util.List;

import ar.com.gestor.stats.exceptions.NeightBirdHoodExpection;
import ar.com.gestor.stats.model.City;

public interface IStats {
	
	public List<City> getTopLocality() throws NeightBirdHoodExpection;

	public List<City> getNeighBirdHodOfLocaly(String name);
	
	public List<City> getFurtherAwayLocations();

}
