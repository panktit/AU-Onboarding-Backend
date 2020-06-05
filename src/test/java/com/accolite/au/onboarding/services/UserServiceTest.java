package com.accolite.au.onboarding.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import com.accolite.au.onboarding.exceptions.InvalidCredentialsException;
import com.accolite.au.onboarding.models.User;
import com.accolite.au.onboarding.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	User user1;
	User user2;
	List<User> list;
	
	@BeforeEach
	public void setup() {
		user1 = new User("test", "test@gmail.com", "secret", "manager");
		user2 = new User("test2", "test2@gmail.com", "secret2", "super");
		list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
	}

	@Test
	void shouldGetAllUsers() {
//		defining repository behavior
		when(userRepository.findAll()).thenReturn(list);

//		calling the service function
		List<User> returnedList = userService.getAllUsers();

//		assertions
		assertThat(returnedList).isEqualTo(list);
		assertEquals(2, returnedList.size());
	}
	
	@Test
	void shouldGetUserById() throws EntityInstanceNotFoundException {
		
		user2.setId(2L);
		
//		defining repository behavior
		when(userRepository.existsById(user2.getId())).thenReturn(true);
		when(userRepository.findById(user2.getId())).thenReturn(java.util.Optional.ofNullable(user2));

//		calling the service function
		User returned = userService.getUser(2L);
		
//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(user2);
	}
	
	@Test
	void shouldThrowEntityInstanceNotFoundException() throws EntityInstanceNotFoundException {
		user2.setId(2L);
//		defining repository behavior
		when(userRepository.existsById(user2.getId())).thenReturn(false);
		
		assertThrows(EntityInstanceNotFoundException.class, () -> {
			userService.getUser(2L);
		});
	}
	
	@Test
	void shouldAllowLogin() throws InvalidCredentialsException, EntityInstanceNotFoundException {
//		defining repository behavior
		when(userRepository.findFirstByEmail(user2.getEmail())).thenReturn(user2);
		
//		calling the service function
		User returned = userService.verifyUser(user2.getEmail(),user2.getPassword());
		
//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(user2);
	}
	
	@Test
	void shouldReturnCorrectAccessLevel() throws EntityInstanceNotFoundException {
//		defining repository behavior
		when(userRepository.findFirstByEmail(user2.getEmail())).thenReturn(user2);
		
//		calling the service function
		User returned = userService.checkAccess(user2.getEmail());
		
//		assertions
		assertNotNull(returned);
		assertThat(returned).isEqualTo(user2);
	}
}
