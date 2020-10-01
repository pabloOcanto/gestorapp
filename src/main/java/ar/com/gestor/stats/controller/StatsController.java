package ar.com.gestor.stats.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.com.gestor.stats.exceptions.NeightBirdHoodExpection;
import ar.com.gestor.stats.exceptions.NotFoundException;
import ar.com.gestor.stats.model.City;
import ar.com.gestor.stats.service.IStats;

@RestController
@RequestMapping(path = "/42i/gestor/stats/v1")
public class StatsController {
	
	@Autowired
	@Qualifier("Service")
	IStats service;
	

	@GetMapping("/isOk")
	public ResponseEntity<?> checkController(){	
		return new ResponseEntity<String>("isWorking", HttpStatus.OK);
	}

	@GetMapping("/searchNeightBirdHood")
	@Cacheable
	public ResponseEntity<?> neightBirdHoods(@RequestParam String locality) throws NotFoundException{
		List<City>neighBirdHood= Optional.ofNullable(service.getNeighBirdHodOfLocaly(locality)).orElseThrow(NotFoundException::new);		
		return new ResponseEntity<>(neighBirdHood, HttpStatus.OK);
	}
	
	@GetMapping("/topLocality")
	public ResponseEntity<?> neightBirdHoods() throws  NeightBirdHoodExpection{
		return new ResponseEntity<>(service.getTopLocality(), HttpStatus.OK);
	}
	
	@GetMapping("/futherAwayLocations")
	public ResponseEntity<?> getFurtherAwayLocations() throws  NeightBirdHoodExpection{
		return new ResponseEntity<>(service.getFurtherAwayLocations(), HttpStatus.OK);
	}
		

}
