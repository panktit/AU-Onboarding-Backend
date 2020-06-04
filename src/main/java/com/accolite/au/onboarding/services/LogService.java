package com.accolite.au.onboarding.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.onboarding.models.Log;
import com.accolite.au.onboarding.repositories.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	public List<Log> getAllLogs() {
        Iterable<Log> logItr = logRepository.findAll();
        List<Log> logs = StreamSupport
        					.stream(logItr.spliterator(), false)
        					.collect(Collectors.toList());
        return logs;
    }
	
	public Log saveLog(Log newLog) {
//		can do server-side validation before saving into db
		newLog.setCreated_at(formatter.format(new Date()));
		return logRepository.save(newLog);
	}
	
	public Log getLog(Long id) {
		if(logRepository.existsById(id))
			return logRepository.findById(id).get();
		else 
			return null;
	}

}
