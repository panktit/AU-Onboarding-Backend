package com.accolite.au.onboarding.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.repositories.UserRepository;

import com.accolite.au.onboarding.models.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {	
        Iterable<User> userItr = userRepository.findAll();
        List<User> users = StreamSupport
        					.stream(userItr.spliterator(), false)
        					.collect(Collectors.toList());
        return users;
    }
	
	public User saveUser(User newUser) {
//		can do server-side validation before saving into db
		return userRepository.save(newUser);
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
//	temp-implementation, need to modify the verification method
	public User verifyUser(String email, String password) {
		User user = userRepository.findFirstByEmail(email);
		if(user!=null && user.getPassword().equals(password))
			return user;
		else
			return null;
				
	}
	
	public User checkAccess (String email) {
		User user = userRepository.findFirstByEmail(email);
		return user;
	}

}
