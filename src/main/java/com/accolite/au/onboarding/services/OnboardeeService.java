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

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.repositories.OnboardeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OnboardeeService {
	
	@Autowired
	private OnboardeeRepository onboardeeRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	
	public List<Onboardee> getAllOnboardees() {
		log.info("Onboardee service call to get all onboardees");
        Iterable<Onboardee> onboardeeItr = onboardeeRepository.findAll();
        List<Onboardee> onboardees = StreamSupport
        					.stream(onboardeeItr.spliterator(), false)
        					.collect(Collectors.toList());
        return onboardees;
    }
	
	public Onboardee saveOnboardee(Onboardee newOnboardee) {
		log.info("Onboardee service call to save new onboardee");
		newOnboardee.setCreated_at(formatter.format(new Date()));
		newOnboardee.setLast_modified(formatter.format(new Date()));
		return onboardeeRepository.save(newOnboardee);
	}
	
	public Onboardee getOnboardee(Long id) throws EntityInstanceNotFoundException {
		log.info("Onboardee service call to get onboardee with id: "+id);
		if(onboardeeRepository.existsById(id))
			return onboardeeRepository.findById(id).get();
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}

	public Onboardee updateOnboardee(Long id, Onboardee onboardee) throws EntityInstanceNotFoundException {
		log.info("Onboardee service call to update onboardee with id: "+id);
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
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}
	
	public String deleteOnboardee(Long id) throws EntityInstanceNotFoundException {
		log.info("Onboardee service call to delete onboardee with id: "+id);
		if(onboardeeRepository.existsById(id)) {
			onboardeeRepository.deleteById(id);
			return "\"Deletion Successful! :D\"";
		}
			
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
			
	}

	public List<Object> getCityData() {
		log.info("Onboardee service call to get joining cities data");
		return onboardeeRepository.findJoiningCityWithCount();
	}

	public Map<String, Integer> getSelectedOnboardeeSkills() {
		log.info("Onboardee service call to get skills of selected candidates");
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
