package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.exceptions.InvalidCredentialsException;
import com.accolite.au.onboarding.models.User;
import com.accolite.au.onboarding.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class UserController {

	// CRUD APIs for User
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		log.info("Get request received for all users");
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		log.info("Get request received for user with id: "+id);
		try {
			return userService.getUser(id);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /user/"+id+": "+e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/login")
	public User verifyUser(@RequestBody ObjectNode objectNode) {
		log.info("Post request received to verify user login");
		String email = objectNode.get("email").asText();
		String password = objectNode.get("password").asText();
		try {
			return userService.verifyUser(email, password);
		} catch(EntityInstanceNotFoundException | InvalidCredentialsException e) {
			log.error("Exception occurred at path /login: "+e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/check")
	public User checkUserAccess(@RequestBody ObjectNode objectNode) {
		log.info("Post request received to check access-level of user");
		String email = objectNode.get("email").asText();
		try {
			return userService.checkAccess(email);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /check: "+e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/user/log")
	public User addUserLog(@RequestBody ObjectNode objectNode) {
		log.info("Post request received to add user-specific logs");
		String name = objectNode.get("name").asText();
		String type = objectNode.get("type").asText();
		String description = objectNode.get("description").asText();
		try {
			return userService.addLog(name, type, description);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /user/log: "+e.getMessage());
			return null;
		}
	}
}
