package com.accolite.au.onboarding.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.services.DemandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class DemandController {
	
	@Autowired
	private DemandService demandService;
	
	@GetMapping("/demands")
	public List<Demand> getAllDemands() {
		log.info("Get request received for all demands");
		return demandService.getAllDemands();
	}
	
	@GetMapping("/demand/{id}")
	public Demand getDemand(@PathVariable Long id) {
		log.info("Get request received for demand with id: "+id);
		try {
			return demandService.getDemand(id);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /demand/"+id+": "+e.getMessage());
			return null;
		}
	}
	
	
	@GetMapping("/demands/skills")
	public Map<String, Integer> getAllDemandSkills() {
		log.info("Get request received for skills of all demands");
		return demandService.getAllDemandSkills();
	}
	
	@PostMapping("/demands")
	public Demand saveDemand(@RequestBody Demand newDemand) {
		log.info("Post request received to save a new demand");
		return demandService.saveDemand(newDemand);
	}

}
