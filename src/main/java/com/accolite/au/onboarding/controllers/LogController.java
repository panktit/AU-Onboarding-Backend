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
import com.accolite.au.onboarding.models.ULog;
import com.accolite.au.onboarding.services.LogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping("/logs")
	public List<ULog> getAllLogs() {
		log.info("Get request received for all logs");
		return logService.getAllLogs();
	}
	
	@GetMapping("/log/{id}")
	public ULog getLog(@PathVariable Long id) {
		log.info("Get request received for log with id: "+id);
		try {
			return logService.getLog(id);
		} catch(EntityInstanceNotFoundException e) {
			log.error("Exception occurred at path /onboardee/"+id+": "+e.getMessage());
			return null;
		}
	}

	
	@PostMapping("/logs")
	public ULog saveLog(@RequestBody ULog newLog) {
		log.info("Post request received to save a new log");
		return logService.saveLog(newLog);
	}

}
