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
import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.repositories.DemandRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class DemandService {
	
	@Autowired
	private DemandRepository demandRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public List<Demand> getAllDemands() {
		log.info("Demand service call to get all demands");
        Iterable<Demand> demandItr = demandRepository.findAll();
        List<Demand> demands = StreamSupport
        					.stream(demandItr.spliterator(), false)
        					.collect(Collectors.toList());
        return demands;
    }
	
	public Demand saveDemand(Demand newDemand) {
		log.info("Demand service call to save new demand");
		newDemand.setCreated_at(formatter.format(new Date()));
		return demandRepository.save(newDemand);
	}
	
	public Demand getDemand(Long id) throws EntityInstanceNotFoundException {
		log.info("Demand service call to get demand with id: "+id);
		if(demandRepository.existsById(id))
			return demandRepository.findById(id).get();
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}

	public Map<String, Integer> getAllDemandSkills() {
		log.info("Demand service call to get skills of all demands");
		Map<String, Integer> skillCount = new HashMap<>();
		List<Demand> demands = getAllDemands();
		for(Demand d : demands) {
			for(Skill s : d.getDmdSkills()) {
				if(skillCount.containsKey(s.getName()))
					skillCount.put(s.getName(), skillCount.get(s.getName())+1);
				else
					skillCount.put(s.getName(), 1);
			}
		}
		return skillCount;
	}
}
