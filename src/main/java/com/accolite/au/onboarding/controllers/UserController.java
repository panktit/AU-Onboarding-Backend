package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.models.User;
import com.accolite.au.onboarding.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin
@RestController
public class UserController {

	// CRUD APIs for User
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}

	
	@PostMapping("/users")
	public User saveUser(@RequestBody User newUser) {
		return userService.saveUser(newUser);
	}
	
	@PostMapping("/login")
	public User verifyUser(@RequestBody ObjectNode objectNode) {
		String email = objectNode.get("email").asText();
		String password = objectNode.get("password").asText();
		return userService.verifyUser(email, password);
	}
	
	@PostMapping("/check")
	public User checkUserAccess(@RequestBody ObjectNode objectNode) {
		String email = objectNode.get("email").asText();
		return userService.checkAccess(email);
	}
	
	@PostMapping("/user/log")
	public User addUserLog(@RequestBody ObjectNode objectNode) {
		String name = objectNode.get("name").asText();
		String type = objectNode.get("type").asText();
		String description = objectNode.get("description").asText();
		return userService.addLog(name, type, description);
	}
}
