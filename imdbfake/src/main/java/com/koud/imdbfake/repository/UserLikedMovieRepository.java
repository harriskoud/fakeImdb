package com.koud.imdbfake.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.koud.imdbfake.model.UserLikedMovies;

@Repository
public interface UserLikedMovieRepository extends JpaRepository<UserLikedMovies, Integer> {

	@Query("SELECT u FROM UserLikedMovies u WHERE u.userId = ?1 and u.movieId = ?2")
	public Optional<UserLikedMovies> getRelationship(int userId, int movieId);

}
