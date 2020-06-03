package com.accolite.au.onboarding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.onboarding.models.Log;
import com.accolite.au.onboarding.services.LogService;

@CrossOrigin
@RestController
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping("/logs")
	public List<Log> getAllLogs() {
		return logService.getAllLogs();
	}
	
	@GetMapping("/log/{id}")
	public Log getLog(@PathVariable Long id) {
		return logService.getLog(id);
	}

	
	@PostMapping("/logs")
	public Log saveLog(@RequestBody Log newLog) {
		return logService.saveLog(newLog);
	}

}
