package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Onboardee;
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
		return onboardeeRepository.findById(id).get();
	}

	public Onboardee updateOnboardee(Long id, Onboardee onboardee) {
		Onboardee ob = onboardeeRepository.findById(id).get();
		
		ob.setName(onboardee.getName());
		ob.setEmail(onboardee.getEmail());
		ob.setDob(onboardee.getDob());
		ob.setMno(onboardee.getMno());
		ob.setObSkills(onboardee.getObSkills());
		
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
	
	public void deleteOnboardee(Long id) {
		onboardeeRepository.deleteById(id);
	}

	public List<Object> getCityData() {
		return onboardeeRepository.findJoiningCityWithCount();
	}

}
