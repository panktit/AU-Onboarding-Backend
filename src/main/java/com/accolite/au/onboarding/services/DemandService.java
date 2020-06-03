package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.repositories.DemandRepository;

@Service
public class DemandService {
	
	@Autowired
	private DemandRepository demandRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public List<Demand> getAllDemands() {
        Iterable<Demand> demandItr = demandRepository.findAll();
        List<Demand> demands = StreamSupport
        					.stream(demandItr.spliterator(), false)
        					.collect(Collectors.toList());
        return demands;
    }
	
	public Demand saveDemand(Demand newDemand) {
		newDemand.setCreated_at(formatter.format(new Date()));
		return demandRepository.save(newDemand);
	}
	
	public Demand getDemand(Long id) {
		return demandRepository.findById(id).get();
	}

}
