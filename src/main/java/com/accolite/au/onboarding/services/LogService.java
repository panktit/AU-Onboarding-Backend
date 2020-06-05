package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.models.ULog;
import com.accolite.au.onboarding.repositories.LogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	public List<ULog> getAllLogs() {
		log.info("Log service call to get all user logs");
        Iterable<ULog> logItr = logRepository.findAll();
        List<ULog> logs = StreamSupport
        					.stream(logItr.spliterator(), false)
        					.collect(Collectors.toList());
        return logs;
    }
	
	public ULog saveLog(ULog newLog) {
		log.info("Log service call to save new user log");
		newLog.setCreated_at(formatter.format(new Date()));
		return logRepository.save(newLog);
	}
	
	public ULog getLog(Long id) throws EntityInstanceNotFoundException {
		log.info("Log service call to get log with id: "+id);
		if(logRepository.existsById(id))
			return logRepository.findById(id).get();
		else {
			log.warn("The requested entity does not exist");
			throw new EntityInstanceNotFoundException();
		}
	}

}
