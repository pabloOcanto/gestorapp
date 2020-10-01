package ar.com.gestor.stats.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class LocationUtilTest {
	
	@Test
	public void distanceBetweenGreenwichVillageAndSoho() {
		//name Greenwich Village in manhatan
		 String source= "40.733572:-74.002742";
		 
		//name soho in manhattan 
		 String target= "40.7229:-73.9988";
		 
		double distanceCalculated = LocationUtil.distance(source, target);	
		double distanceExpected = 1.77;		
		assertEquals(distanceExpected, distanceCalculated,15); // con 1 km de diferencia
		
	}
	
	@Test
	public void distanceSanJustoAndPalermo() {
		//name San Justo Bs
		 String source= "-34.68333:-58.5519";
		 
		//name Palermo in Bs As. 
		 String target= "-34.58889:-58.43056";
		 
		double distanceCalculated = LocationUtil.distance(source, target);	
		assertTrue(distanceCalculated<50); // con 1 km de diferencia
		
	}
	
	
	@Test
	public void distancePalermoWirthSelf() {
		//name San Justo Bs
		 String source= "-34.58889:-58.43056";
		 
		//name Palermo in manhattan 
		 String target= "-34.58889:-58.43056";
		 
		double distanceCalculated = LocationUtil.distance(source, target);	
		assertTrue(distanceCalculated==0); // con 1 km de diferencia
		
	}
	

}
