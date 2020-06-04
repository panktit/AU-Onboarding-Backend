package com.accolite.au.onboarding.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.repositories.SkillRepository;

@Service
public class SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	
	public List<Skill> getAllSkills() {
        Iterable<Skill> skillItr = skillRepository.findAll();
        List<Skill> skills = StreamSupport
        					.stream(skillItr.spliterator(), false)
        					.collect(Collectors.toList());
        return skills;
    }
	
	
	public Skill getSkill(Long id) {
		if(skillRepository.existsById(id))
			return skillRepository.findById(id).get();
		else
			return null;
	}
	
	public Skill saveSkill(Skill newSkill) {
		System.out.println("Create skill: "+newSkill);
		return skillRepository.save(newSkill);
	}
	
	public long totalSkills() {
		return skillRepository.count();
	}

}
