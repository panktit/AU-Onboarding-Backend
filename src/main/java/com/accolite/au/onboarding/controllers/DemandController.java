package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.services.DemandService;

@CrossOrigin
@RestController
public class DemandController {
	
	@Autowired
	private DemandService demandService;
	
	@GetMapping("/demands")
	public List<Demand> getAllDemands() {
		return demandService.getAllDemands();
	}
	
	@GetMapping("/demand/{id}")
	public Demand getDemand(@PathVariable Long id) {
		return demandService.getDemand(id);
	}

	
	@PostMapping("/demands")
	public Demand saveDemand(@RequestBody Demand newDemand) {
		return demandService.saveDemand(newDemand);
	}

}
