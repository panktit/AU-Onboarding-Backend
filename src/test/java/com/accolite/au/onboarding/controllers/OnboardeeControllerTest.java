package com.accolite.au.onboarding.controllers;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.accolite.au.onboarding.models.Onboardee;
import com.accolite.au.onboarding.services.OnboardeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = OnboardeeController.class)
@ActiveProfiles("test")
class OnboardeeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OnboardeeService onboardeeService;

	@Autowired
	private ObjectMapper objectMapper;

	private List<Onboardee> list;

	@BeforeEach
	void setUp() {
		this.list = new ArrayList<>();
		this.list.add(new Onboardee(1L, "onboardee1", "pnd1"));
		this.list.add(new Onboardee(2L, "onboardee2", "pnd2"));
		this.list.add(new Onboardee(3L, "onboardee3", "pnd3"));

	}

	@Test
	void shouldFetchAllOnboardees() throws Exception {

		when(onboardeeService.getAllOnboardees()).thenReturn(list);

		this.mockMvc.perform(get("/onboardees")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void shouldFetchOneOnboardeeById() throws Exception {
		final Long id = 1L;
		final Onboardee onboardee = new Onboardee(1L, "test", "pending");

		when(onboardeeService.getOnboardee(id)).thenReturn(onboardee);

		this.mockMvc.perform(get("/onboardee/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(onboardee.getName())))
				.andExpect(jsonPath("$.obStatus", is(onboardee.getObStatus())));
	}

	@Test
	void shouldCreateNewOnboardee() throws Exception {

		Onboardee onboardee = new Onboardee(null, "post test", "pnd");
		Onboardee returned = new Onboardee(null, "post test", "pnd");
		
		when(onboardeeService.saveOnboardee(onboardee)).thenReturn(returned);

		this.mockMvc
				.perform(post("/onboardees").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(onboardee)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is(onboardee.getName())))
				.andExpect(jsonPath("$.obStatus", is(onboardee.getObStatus())));
	}
	
	@Test
    void shouldUpdateOnboardee() throws Exception {
        Long id = 1L;
        Onboardee onboardee = new Onboardee(id, "test", "pnd");
        Onboardee updated = new Onboardee(id, "test", "new");
        
        when(onboardeeService.getOnboardee(id)).thenReturn(onboardee);
        when(onboardeeService.updateOnboardee(id, onboardee)).thenReturn(updated);
        
        this.mockMvc.perform(put("/onboardee/{id}", onboardee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(onboardee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updated.getName())))
                .andExpect(jsonPath("$.obStatus", is(updated.getObStatus())));
    }
	
	@Test
    void shouldDeleteOnboardee() throws Exception {
        Long id = 1L;
        Onboardee onboardee = new Onboardee(id, "test", "pnd");
        
        when(onboardeeService.getOnboardee(id)).thenReturn(onboardee);
                
        when(onboardeeService.deleteOnboardee(id)).thenReturn(onboardee);

        this.mockMvc.perform(delete("/onboardee/{id}", onboardee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(onboardee.getName())))
                .andExpect(jsonPath("$.obStatus", is(onboardee.getObStatus())));
    }

}
