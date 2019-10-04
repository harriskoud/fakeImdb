package com.koud.imdbfake.resource;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koud.imdbfake.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

	Optional<Authority> findByuserId(Long id);

}
