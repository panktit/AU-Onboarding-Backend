package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.repositories.OnboardeeRepository;

@Service
public class OnboardeeService {
	
	@Autowired
	private OnboardeeRepository onboardeeRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	
	public List<Onboardee> getAllOnboardees() {

        Iterable<Onboardee> onboardeeItr = onboardeeRepository.findAll();
        List<Onboardee> onboardees = StreamSupport
        					.stream(onboardeeItr.spliterator(), false)
        					.collect(Collectors.toList());
        return onboardees;
    }
	
	public Onboardee saveOnboardee(Onboardee newOnboardee) {
//		can do server-side validation before saving into db
		System.out.println("To create: "+newOnboardee);
		newOnboardee.setCreated_at(formatter.format(new Date()));
		newOnboardee.setLast_modified(formatter.format(new Date()));
		return onboardeeRepository.save(newOnboardee);
	}
	
	public Onboardee getOnboardee(Long id) {
		if(onboardeeRepository.existsById(id))
			return onboardeeRepository.findById(id).get();
		else
			return null;
	}

	public Onboardee updateOnboardee(Long id, Onboardee onboardee) {
		if(onboardeeRepository.existsById(id)) {
			Onboardee ob = onboardeeRepository.findById(id).get();
			
			ob.setName(onboardee.getName());
			ob.setEmail(onboardee.getEmail());
			ob.setDob(onboardee.getDob());
			ob.setMno(onboardee.getMno());
			ob.setObSkills(onboardee.getObSkills());
			
			ob.setMappedDemand(onboardee.getMappedDemand());
			ob.setJoiningDate(onboardee.getJoiningDate());
			ob.setJoiningCity(onboardee.getJoiningCity());
			ob.setJoiningAddress(onboardee.getJoiningAddress());
			
			ob.setObDate(onboardee.getObDate());
			ob.setObStatus(onboardee.getObStatus());
			ob.setBgc(onboardee.getBgc());
			ob.setGraduation(onboardee.getGraduation());
			ob.setObFormalities(onboardee.getObFormalities());
			ob.setEta(onboardee.getEta());
			
			ob.setLast_modified(formatter.format(new Date()));
			return onboardeeRepository.save(ob);
		}
		else
			return null;
	}
	
	public String deleteOnboardee(Long id) {
		if(onboardeeRepository.existsById(id)) {
			onboardeeRepository.deleteById(id);
			return "\"Deletion Successful! :D\"";
		}
			
		else
			return "\"Onboardee not found! :(\"";
			
	}

	public List<Object> getCityData() {
		return onboardeeRepository.findJoiningCityWithCount();
	}

	public Map<String, Integer> getSelectedOnboardeeSkills() {
		List<Onboardee> obList = getAllOnboardees();
		Map<String, Integer> skillCount = new HashMap<>();
		for(Onboardee ob : obList) {
			if(ob.getMappedDemand()!=null) {  // if selected
				for(Skill s : ob.getObSkills()) {
					if(skillCount.containsKey(s.getName()))
						skillCount.put(s.getName(), skillCount.get(s.getName())+1);
					else
						skillCount.put(s.getName(), 1);
				}

			}
		}
		return skillCount;
	}

}
