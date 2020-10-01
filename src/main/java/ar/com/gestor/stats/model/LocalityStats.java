package ar.com.gestor.stats.model;

public class LocalityStats {
	private String name;
	private Integer neightBirdHoods;
	
	public LocalityStats() {}
	
	public LocalityStats(String name, Integer neightBirdHood) {
		super();
		this.name = name;
		this.neightBirdHoods = neightBirdHood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNeightBirdHoods() {
		return neightBirdHoods;
	}

	public void setNeightBirdHood(Integer neightBirdHood) {
		this.neightBirdHoods = neightBirdHood;
	}
	
	
	
	
}
