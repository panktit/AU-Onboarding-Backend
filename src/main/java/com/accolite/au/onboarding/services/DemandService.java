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

import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.models.Skill;
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
		if(demandRepository.existsById(id))
			return demandRepository.findById(id).get();
		else
			return null;
	}

	public List<Skill> getDemandSkills(Long id) {
		if(demandRepository.existsById(id)) {
			Demand dmd = demandRepository.findById(id).get();
			return dmd.getDmdSkills();
		}
		else
			return null;
	}

	public Map<String, Integer> getAllDemandSkills() {
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
