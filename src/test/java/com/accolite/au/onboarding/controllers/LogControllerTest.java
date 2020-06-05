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

import com.accolite.au.onboarding.models.ULog;
import com.accolite.au.onboarding.services.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = LogController.class)
@ActiveProfiles("test")
public class LogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LogService logService;

	@Autowired
	private ObjectMapper objectMapper;

	private List<ULog> list;

	@BeforeEach
	void setUp() {
		this.list = new ArrayList<>();
		this.list.add(new ULog("log1", "description1"));
		this.list.add(new ULog("log2", "description2"));
		this.list.add(new ULog("log3", "description3"));

	}

	@Test
	void shouldFetchAllULogs() throws Exception {

		when(logService.getAllLogs()).thenReturn(list);

		this.mockMvc.perform(get("/logs")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void shouldFetchOneULogById() throws Exception {
		final Long id = 1L;
		final ULog log = new ULog("test", "pending");

		when(logService.getLog(id)).thenReturn(log);

		this.mockMvc.perform(get("/log/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.type", is(log.getType())))
				.andExpect(jsonPath("$.description", is(log.getDescription())));
	}
	
	@Test
	void shouldCreateNewULog() throws Exception {

		ULog log = new ULog("post test", "pnd");
		ULog returned = new ULog("post test", "pnd");
		
		when(logService.saveLog(log)).thenReturn(returned);

		this.mockMvc
				.perform(post("/logs").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(log)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.type", is(log.getType())))
				.andExpect(jsonPath("$.description", is(log.getDescription())));
	}
}
