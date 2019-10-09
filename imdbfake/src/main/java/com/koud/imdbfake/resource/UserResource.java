package com.koud.imdbfake.resource;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koud.imdbfake.model.User;
import com.koud.imdbfake.service.AuthorityService;
import com.koud.imdbfake.service.UserService;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserResource {

	private static Logger LOG = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	@GetMapping
	public @ResponseBody List<User> getUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> getUserById(@PathVariable int id) {
		Optional<User> user = userService.findUserById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> registerUser(@RequestBody User user) {

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User registeredUser = userService.registerUser(user);
		
		LOG.info("Registration of: " + registeredUser);
		
		authorityService.createNewAuthority(registeredUser);

		return ResponseEntity.ok(registeredUser);
	}
	

}
