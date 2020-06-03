package com.accolite.au.onboarding.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.au.onboarding.models.Onboardee;

@Repository
public interface OnboardeeRepository extends CrudRepository<Onboardee, Long>{
	
	@Query(nativeQuery = true, value="select count(joining_city) as count,joining_city from Onboardee group by joining_city")
	List<Object> findJoiningCityWithCount();
}

