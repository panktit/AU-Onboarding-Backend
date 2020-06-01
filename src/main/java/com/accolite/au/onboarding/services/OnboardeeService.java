package com.accolite.au.onboarding.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.models.User;
import com.accolite.au.onboarding.repositories.OnboardeeRepository;

@Service
public class OnboardeeService {
	
	@Autowired
	private OnboardeeRepository onboardeeRepository;
	
	public List<Onboardee> getAllOnboardees() {

        Iterable<Onboardee> onboardeeItr = onboardeeRepository.findAll();
        List<Onboardee> onboardees = StreamSupport
        					.stream(onboardeeItr.spliterator(), false)
        					.collect(Collectors.toList());
        return onboardees;
    }
	
	public Onboardee saveOnboardee(Onboardee newOnboardee) {
//		can do server-side validation before saving into db
		return onboardeeRepository.save(newOnboardee);
	}
	
	public Onboardee getOnboardee(Long id) {
		return onboardeeRepository.findById(id).get();
	}

	public Onboardee updateOnboardee(Long id, Onboardee onboardee) {
		Onboardee ob = onboardeeRepository.findById(id).get();
		ob.setName(onboardee.getName());
		ob.setEmail(onboardee.getEmail());
		ob.setMno(onboardee.getMno());
		ob.setJoiningCity(onboardee.getJoiningCity());
		ob.setObStatus(onboardee.getObStatus());
		ob.setEta(onboardee.getEta());
		return onboardeeRepository.save(ob);
	}
	
	public void deleteOnboardee(Long id) {
		onboardeeRepository.deleteById(id);
	}

}
