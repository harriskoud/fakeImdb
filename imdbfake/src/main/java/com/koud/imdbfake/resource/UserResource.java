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

import com.koud.imdbfake.model.Authority;
import com.koud.imdbfake.model.User;
import com.koud.imdbfake.repository.UserRepository;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserResource {

	private static Logger LOG = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;

	@GetMapping
	public @ResponseBody List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> getUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> registerUser(@RequestBody User user) {

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User registeredUser = userRepository.save(user);
		
		LOG.info("Registration of: " + registeredUser);
		
		Authority authority = Authority.builder().authRole("USER").userId(registeredUser.getUserId())
				.userName(registeredUser.getUsername()).build();
		authorityRepository.save(authority);

		return ResponseEntity.ok(registeredUser);
	}
	

}
