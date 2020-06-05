package com.accolite.au.onboarding.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.services.OnboardeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class OnboardeeController {
	
	@Autowired
	private OnboardeeService onboardeeService;
	
	@GetMapping("/onboardees")
	public List<Onboardee> getAllOnboardees() {
		log.info("Get request received for all onboardees");
		List<Onboardee> obs = onboardeeService.getAllOnboardees();
		return obs;
	}
	
	@GetMapping("/onboardee/{id}")
	public Onboardee getOnboardee(@PathVariable Long id) {
		log.info("Get request received for onboardee with id: "+id);
		try {
			return onboardeeService.getOnboardee(id);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /onboardee/"+id+": "+e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/onboardees/selected/skills")
	public Map<String, Integer> getSelectedOnboardeeSkills() {
		log.info("Get request received to get skills of all selected candidates");
		return onboardeeService.getSelectedOnboardeeSkills();
	}
	
	@GetMapping("/onboardees/joiningCities")
	public List<Object> getCityNames() {
		log.info("Get request received for all joining cities");
		return onboardeeService.getCityData();
	}
		
	@PostMapping("/onboardees")	
	public Onboardee saveOnboardee(@RequestBody Onboardee newOnboardee) {
		log.info("Post request received to save a new onboardee");
		return onboardeeService.saveOnboardee(newOnboardee);
	}
	
	@PutMapping("/onboardee/{id}")  
	public Onboardee update(@PathVariable Long id, @RequestBody Onboardee onboardee) {
		log.info("Put request received to update onoardee with id: "+id);
		try {
			return onboardeeService.updateOnboardee(id, onboardee);
		} catch (EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /onboardee/"+id+": "+e.getMessage());
			return null;
		}  
	}  
	
	@DeleteMapping("/onboardee/{id}")
	public Onboardee deleteOnboardee(@PathVariable Long id) {
		log.info("Delete request received to delete onboardee with id: "+id);
		try {
			return onboardeeService.deleteOnboardee(id);
		} catch (EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /onboardee/"+id+": "+e.getMessage());
			return null;
		}
	}
}
