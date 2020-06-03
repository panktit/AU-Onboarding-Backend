package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.services.SkillService;


@CrossOrigin
@RestController
public class SkillsController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/skills")
	public List<Skill> getAllSkills() {
		return skillService.getAllSkills();
	}

	@GetMapping("/skill/{id}")
	public Skill getSkill(@PathVariable Long id) {
		return skillService.getSkill(id);
	}

	@PostMapping("/skills")
	public Skill saveSkill(@RequestBody Skill newSkill) {
		return skillService.saveSkill(newSkill);
	}

}
