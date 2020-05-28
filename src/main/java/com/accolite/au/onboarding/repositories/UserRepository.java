package com.accolite.au.onboarding.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.au.onboarding.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
//	can user Repository<> for custom queries
	User findFirstByEmail(String email);

}
