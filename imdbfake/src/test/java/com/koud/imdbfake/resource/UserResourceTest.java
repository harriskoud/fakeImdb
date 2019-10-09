package com.koud.imdbfake.resource;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.koud.imdbfake.model.User;
import com.koud.imdbfake.repository.UserRepository;
import com.koud.imdbfake.service.UserService;

@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserResourceTest {

	@MockBean
	private UserService userService;
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserResource userResource;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetUserById() throws JsonMappingException, JsonProcessingException {
		User stubUser = createUser();
		when(userService.findUserById(anyInt())).thenReturn(Optional.of(stubUser));
		ResponseEntity userById = userResource.getUserById(336812683);
		User user = (User)userById.getBody(); 
		assertTrue(user != null);

	}

	private User createUser() {

		return User.builder().firstname("Harris").lastname("Koud").username("HarrisKoud").creationDate(new Date())
				.email("hehfuwh@gmail.com").isLocked(false).timesBeforeLocked(0)
				.password("ewfwjefjwijfwjfjj234j432rujnugjneurhnn").build();
	}
	
}
