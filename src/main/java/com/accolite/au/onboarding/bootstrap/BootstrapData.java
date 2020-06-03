package com.accolite.au.onboarding.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.accolite.au.onboarding.models.Address;
import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.models.Skill;
import com.accolite.au.onboarding.services.OnboardeeService;
import com.accolite.au.onboarding.services.SkillService;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private OnboardeeService onboardeeService;
	private SkillService skillService;
	
	public BootstrapData(OnboardeeService onboardeeService, SkillService skillService) {
		this.onboardeeService = onboardeeService;
		this.skillService = skillService;
	}

	@Override
	public void run(String... args) throws Exception {
//		Address add1 = new Address("221B", "Baker Steet", "London ", "state", "country", 289828);
//		
//		Skill skill1 = new Skill("Crime Detection");
//		Onboardee ob1 = new Onboardee("Sherlock Homes", "pending");
//		Onboardee ob2 = new Onboardee("James Watson", "pending");
		
//		skill1.getOnboardees().add(ob1);
//		skill1.getOnboardees().add(ob2);
//		skillService.saveSkill(skill1);
//		ob1.setJoiningAddress(add1);
		
//		
//		skill1.getOnboardees().add(ob1);
//		ob1.getObSkills().add(skill1);
//		ob2.getObSkills().add(skill1);
//		onboardeeService.saveOnboardee(ob1);
//		onboardeeService.saveOnboardee(ob2);
//		
//		
//		Skill skill2 = new Skill("Python");
//		Onboardee ob2 = new Onboardee("Bran Stark", "pending");
//		
//		skill1.getOnboardees().add(ob2);
//		ob1.getObSkills().add(skill2);
//		
//		skillService.saveSkill(skill2);
//		
//		System.out.println(">>>>>>>> Skill count: "+skillService.totalSkills());
	}

}
