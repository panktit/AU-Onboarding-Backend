package com.accolite.au.onboarding.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.au.onboarding.models.Demand;
import com.accolite.au.onboarding.services.DemandService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = DemandController.class)
@ActiveProfiles("test")
public class DemandControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DemandService demandService;

	@Autowired
	private ObjectMapper objectMapper;

	private List<Demand> list;

	@BeforeEach
	void setUp() {
		this.list = new ArrayList<>();
		this.list.add(new Demand("demand1", "team1"));
		this.list.add(new Demand("demand2", "team2"));
		this.list.add(new Demand("demand3", "team3"));

	}

	@Test
	void shouldFetchAllDemands() throws Exception {

		when(demandService.getAllDemands()).thenReturn(list);

		this.mockMvc.perform(get("/demands")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void shouldFetchOneDemandById() throws Exception {
		final Long id = 1L;
		final Demand demand = new Demand("test", "pending");

		when(demandService.getDemand(id)).thenReturn(demand);

		this.mockMvc.perform(get("/demand/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.role", is(demand.getRole())))
				.andExpect(jsonPath("$.team", is(demand.getTeam())));
	}
	
	@Test
	void shouldCreateNewDemand() throws Exception {

		Demand demand = new Demand("post test", "pnd");
		Demand returned = new Demand("post test", "pnd");
		
		when(demandService.saveDemand(demand)).thenReturn(returned);

		this.mockMvc
				.perform(post("/demands").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(demand)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.role", is(demand.getRole())))
				.andExpect(jsonPath("$.team", is(demand.getTeam())));
	}

}
