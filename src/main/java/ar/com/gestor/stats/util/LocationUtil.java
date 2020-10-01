package ar.com.gestor.stats.util;

public class LocationUtil {
	
	static final double  R = 6371.01; // km
	
	
	public static Double distance(String source, String target) {
		
		Double lat1 = Double.valueOf(source.split(":")[0]);
		Double lon1 = Double.valueOf(source.split(":")[1]);
		
		Double lat2 = Double.valueOf(target.split(":")[0]);
		Double lon2 = Double.valueOf(target.split(":")[1]);
		
		lat1 = Math.abs(lat1);
		lat2 = Math.abs(lat2);
		
		lon1 = Math.abs(lon1);
		lon2 = Math.abs(lon2);
		
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = (R * c ); //km
	    
//	    distance= Math.round(distance*100)/100;		
		
	    return distance;
	}
}
