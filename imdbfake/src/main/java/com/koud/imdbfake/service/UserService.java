package com.koud.imdbfake.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koud.imdbfake.model.User;
import com.koud.imdbfake.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findUserById(int id){
		return userRepository.findById(id);
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User registerUser(User user){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
		
	}

}
