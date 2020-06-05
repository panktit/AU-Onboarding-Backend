package com.accolite.au.onboarding.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accolite.au.onboarding.exceptions.EntityInstanceNotFoundException;
import com.accolite.au.onboarding.models.ULog;
import com.accolite.au.onboarding.repositories.LogRepository;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LogService logService;
	
	ULog log1;
	ULog log2;
	List<ULog> logList;

	@BeforeEach
	public void setup() {
		log1 = new ULog("INFO", "desc1", "05/06/2020");
		log2 = new ULog("WARN", "desc2", "05/06/2020");
		logList = new ArrayList<>();
		logList.add(log1);
		logList.add(log2);
	}

	@Test
	void shouldSaveLogSuccessFully() {
//		defining repository behavior
		when(logRepository.save(any(ULog.class))).thenReturn(log1);

//		calling the service function
		ULog returnedOb = logService.saveLog(log1);

//		assertions
		assertNotNull(returnedOb);
		assertThat(returnedOb).isEqualTo(log1);
	}

	@Test
	void shouldGetAllLogs() {
//		defining repository behavior
		when(logRepository.findAll()).thenReturn(logList);

//		calling the service function
		List<ULog> returnedList = logService.getAllLogs();

//		assertions
		assertThat(returnedList).isEqualTo(logList);
		assertEquals(2, returnedList.size());
	}

	@Test
	void shouldGetLogById() throws EntityInstanceNotFoundException {
		log2.setId(2L);
//		defining repository behavior
		when(logRepository.existsById(log2.getId())).thenReturn(true);
		when(logRepository.findById(log2.getId())).thenReturn(java.util.Optional.ofNullable(log2));

//		calling the service function
		ULog returned = logService.getLog(2L);
		
//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(log2);
	}
	
	@Test
	void shouldThrowEntityInstanceNotFoundException() throws EntityInstanceNotFoundException {
		log2.setId(2L);
//		defining repository behavior
		when(logRepository.existsById(log2.getId())).thenReturn(false);
		
		assertThrows(EntityInstanceNotFoundException.class, () -> {
			logService.getLog(2L);
		});
	}
}
