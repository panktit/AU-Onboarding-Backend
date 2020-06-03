package com.accolite.au.onboarding.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.au.onboarding.models.Demand;

@Repository
public interface DemandRepository extends CrudRepository<Demand, Long> {

}
