package com.koud.imdbfake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koud.imdbfake.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
