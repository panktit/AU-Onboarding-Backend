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
import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.repositories.OnboardeeRepository;

@ExtendWith(MockitoExtension.class)
class OnboardeeServiceTest {

	@Mock
	private OnboardeeRepository onboardeeRepository;

	@InjectMocks
	private OnboardeeService onboardeeService;

	Onboardee ob1;
	Onboardee ob2;
	List<Onboardee> obList;

	@BeforeEach
	public void setup() {
		ob1 = new Onboardee(1L, "Test One", "Pending");
		ob2 = new Onboardee(2L, "Test Two", "Completed");
		obList = new ArrayList<>();
		obList.add(ob1);
		obList.add(ob2);
	}

	@Test
	void shouldSaveOnboardeeSuccessFully() {
//		defining repository behavior
		when(onboardeeRepository.save(any(Onboardee.class))).thenReturn(ob1);

//		calling the service function
		Onboardee returnedOb = onboardeeService.saveOnboardee(ob1);

//		assertions
		assertNotNull(returnedOb);
		assertThat(returnedOb).isEqualTo(ob1);
	}

	@Test
	void shouldGetAllOnboardees() {
//		defining repository behavior
		when(onboardeeRepository.findAll()).thenReturn(obList);

//		calling the service function
		List<Onboardee> returnedObList = onboardeeService.getAllOnboardees();

//		assertions
		assertThat(returnedObList).isEqualTo(obList);
		assertEquals(2, returnedObList.size());
	}

	@Test
	void shouldGetOnboardeeById() throws EntityInstanceNotFoundException {
//		defining repository behavior
		when(onboardeeRepository.existsById(ob2.getId())).thenReturn(true);
		when(onboardeeRepository.findById(ob2.getId())).thenReturn(java.util.Optional.ofNullable(ob2));

//		calling the service function
		Onboardee returnedOb = onboardeeService.getOnboardee(2L);
		
//		assertions
		assertNotNull(returnedOb);
		assertThat(returnedOb).isEqualTo(ob2);
	}
	
	@Test
	void shouldThrowEntityInstanceNotFoundException() throws EntityInstanceNotFoundException {
//		defining repository behavior
		when(onboardeeRepository.existsById(ob2.getId())).thenReturn(false);
		
		assertThrows(EntityInstanceNotFoundException.class, () -> {
			onboardeeService.getOnboardee(2L);
		});
	}
	
	@Test
	void shouldUpdateOnboardeeById() throws EntityInstanceNotFoundException {
		
		Onboardee updated = new Onboardee(1L, "Updated", "Completed");
		
//		defining repository behavior
		
		when(onboardeeRepository.existsById(ob1.getId())).thenReturn(true);
		when(onboardeeRepository.findById(ob1.getId())).thenReturn(java.util.Optional.ofNullable(ob1));
		when(onboardeeRepository.save(ob1)).thenReturn(ob1);
		
//		calling the service function
		Onboardee returnedOb = onboardeeService.updateOnboardee(1L, updated);
		
//		assertions
		assertNotNull(returnedOb);
		assertThat(returnedOb).isEqualTo(updated);
	}
	
	@Test
	void shouldDeleteOnboardeeById() throws EntityInstanceNotFoundException {
		
		Onboardee deleted = new Onboardee();
		
//		defining repository behavior
		when(onboardeeRepository.existsById(ob2.getId())).thenReturn(true);
		when(onboardeeRepository.findById(ob2.getId())).thenReturn(java.util.Optional.ofNullable(deleted));

//		calling the service function
		Onboardee returnedOb = onboardeeService.deleteOnboardee(2L);
		
//		assertions
		assertNotNull(returnedOb);
		assertThat(returnedOb).isEqualTo(deleted);
	}
}
