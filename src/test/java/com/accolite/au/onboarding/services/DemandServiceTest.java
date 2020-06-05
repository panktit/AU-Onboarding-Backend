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
import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.repositories.DemandRepository;

@ExtendWith(MockitoExtension.class)
class DemandServiceTest {

	@Mock
	private DemandRepository demandRepository;

	@InjectMocks
	private DemandService demandService;

	Demand dmd1;
	Demand dmd2;
	List<Demand> dmdList;
	
	@BeforeEach
	public void setup() {
		dmd1 = new Demand("Test One", "123", "dept1", "team1");
		dmd2 = new Demand("Test Two", "456", "dept2", "team2");
		dmdList = new ArrayList<>();
		dmdList.add(dmd1);
		dmdList.add(dmd2);
	}

	@Test
	void shouldSaveDemandSuccessFully() {
//		defining repository behavior
		when(demandRepository.save(any(Demand.class))).thenReturn(dmd1);

//		calling the service function
		Demand returned = demandService.saveDemand(dmd1);

//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(dmd1);
	}

	@Test
	void shouldGetAllDemands() {
//		defining repository behavior
		when(demandRepository.findAll()).thenReturn(dmdList);

//		calling the service function
		List<Demand> returnedList = demandService.getAllDemands();

//		assertions
		assertThat(returnedList).isEqualTo(dmdList);
		assertEquals(2, returnedList.size());
	}

	@Test
	void shouldGetDemandById() throws EntityInstanceNotFoundException {
		dmd2.setId(2L);
//		defining repository behavior
		when(demandRepository.existsById(dmd2.getId())).thenReturn(true);
		when(demandRepository.findById(dmd2.getId())).thenReturn(java.util.Optional.ofNullable(dmd2));

//		calling the service function
		Demand returned = demandService.getDemand(2L);
		
//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(dmd2);
	}
	
	@Test
	void shouldThrowEntityInstanceNotFoundException() throws EntityInstanceNotFoundException {
		dmd2.setId(2L);
//		defining repository behavior
		when(demandRepository.existsById(dmd2.getId())).thenReturn(false);
		
		assertThrows(EntityInstanceNotFoundException.class, () -> {
			demandService.getDemand(2L);
		});
	}

}
