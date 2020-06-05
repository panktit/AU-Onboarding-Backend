package com.accolite.au.onboarding.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.au.onboarding.models.User;
import com.accolite.au.onboarding.services.UserService;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private List<User> list;

	@BeforeEach
	void setUp() {
		this.list = new ArrayList<>();
		this.list.add(new User("user1", "pnd1@gmail.com"));
		this.list.add(new User("user2", "pnd2@gmail.com"));
		this.list.add(new User("user3", "pnd3@gmail.com"));

	}

	@Test
	void shouldFetchAllUsers() throws Exception {

		when(userService.getAllUsers()).thenReturn(list);

		this.mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void shouldFetchOneUserById() throws Exception {
		final Long id = 1L;
		final User user = new User("test", "test@gmail.com");

		when(userService.getUser(id)).thenReturn(user);

		this.mockMvc.perform(get("/user/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(user.getName())))
				.andExpect(jsonPath("$.email", is(user.getEmail())));
	}

}
