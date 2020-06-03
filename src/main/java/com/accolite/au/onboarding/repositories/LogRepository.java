package com.accolite.au.onboarding.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.au.onboarding.models.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long>{

}
