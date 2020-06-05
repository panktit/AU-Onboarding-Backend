package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.exceptions.InvalidCredentialsException;
import com.accolite.au.onboarding.models.ULog;
import com.accolite.au.onboarding.models.User;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public List<User> getAllUsers() {
		log.info("Onboardee service call to get all onboardees");
		Iterable<User> userItr = userRepository.findAll();
		List<User> users = StreamSupport.stream(userItr.spliterator(), false).collect(Collectors.toList());
		return users;
	}

	public User saveUser(User newUser) {
		log.info("Onboardee service call to save new onboardee");
		return userRepository.save(newUser);
	}

	public User getUser(Long id) throws EntityInstanceNotFoundException {
		log.info("Onboardee service call to get onboardee with id: " + id);
		if (userRepository.existsById(id))
			return userRepository.findById(id).get();
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}

	public User verifyUser(String email, String password) throws EntityInstanceNotFoundException, InvalidCredentialsException  {
		log.info("User service call to verfiy user credentials for login");
		User user = userRepository.findFirstByEmail(email);
		if (user == null) {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
		else if(user!=null	&& user.getPassword().equals(password))
			return user;
		else {
			log.warn("Invalid user credentials");
			throw new InvalidCredentialsException();
		}
	}

	public User checkAccess(String email) throws EntityInstanceNotFoundException {
		log.info("User service call to check access level of a user");
		User user = userRepository.findFirstByEmail(email);
		if (user != null)
			return user;
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}

	}

	public User addLog(String name, String type, String description) throws EntityInstanceNotFoundException {
		log.info("User service call to get logs of a particular user");
		User user = userRepository.findFirstByName(name);
		if (user != null) {
			user.getLogs().add(new ULog(type, description, formatter.format(new Date())));
			return userRepository.save(user);
		} else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}

}
