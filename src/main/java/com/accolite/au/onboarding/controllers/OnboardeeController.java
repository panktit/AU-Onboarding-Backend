package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.services.OnboardeeService;

@CrossOrigin
@RestController
public class OnboardeeController {
	
	@Autowired
	private OnboardeeService onboardeeService;
	
	@GetMapping("/onboardees")
	public List<Onboardee> getAllOnboardees() {
		List<Onboardee> obs = onboardeeService.getAllOnboardees();
		return obs;
	}
	
	@GetMapping("/onboardee/{id}")
	public Onboardee getOnboardee(@PathVariable Long id) {
		return onboardeeService.getOnboardee(id);
	}
		
	@PostMapping("/onboardees")
	public Onboardee saveOnboardee(@RequestBody Onboardee newOnboardee) {
		return onboardeeService.saveOnboardee(newOnboardee);
	}
	
	@PutMapping("/onboardee/{id}")  
	private Onboardee update(@PathVariable Long id, @RequestBody Onboardee onboardee) {
		return onboardeeService.updateOnboardee(id, onboardee);  
	}  
	
	@DeleteMapping("/onboardee/{id}")
	private void deleteOnboardee(@PathVariable Long id) {
		onboardeeService.deleteOnboardee(id);
	}
}