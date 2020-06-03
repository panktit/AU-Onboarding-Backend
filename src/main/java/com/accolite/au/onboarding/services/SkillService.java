package com.accolite.au.onboarding.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.repositories.SkillRepository;

@Service
public class SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	
	public Skill saveSkill(Skill newSkill) {
		System.out.println("Create skill: "+newSkill);
		return skillRepository.save(newSkill);
	}
	
	public long totalSkills() {
		return skillRepository.count();
	}

}
