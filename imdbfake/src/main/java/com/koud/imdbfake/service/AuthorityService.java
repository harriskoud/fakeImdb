package com.koud.imdbfake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koud.imdbfake.model.Authority;
import com.koud.imdbfake.model.User;
import com.koud.imdbfake.resource.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public void createNewAuthority(User user) {
		Authority authority = Authority.builder().authRole("USER").userId(user.getUserId()).userName(user.getUsername())
				.build();
		authorityRepository.save(authority);
	}
}
