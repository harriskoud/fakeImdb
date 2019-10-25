package com.koud.imdbfake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koud.imdbfake.model.Movie;
import com.koud.imdbfake.model.UserLikedMovies;
import com.koud.imdbfake.repository.UserLikedMovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private UserLikedMovieRepository userLikedMovieRepository;

	public Movie likeMovie(Movie movie, boolean like, int userId) {
		int likes = movie.getLikes();
		if(like) {
			userLikedMovieRepository.save(UserLikedMovies.builder().userId(userId).favour(1).movieId(movie.getMovieId()).userId(userId).build());
			movie.setLikes(++likes);
		}else {
			movie.setLikes(--likes);
			userLikedMovieRepository.save(UserLikedMovies.builder().userId(userId).favour(0).movieId(movie.getMovieId()).userId(userId).build());		
			}
		return movie;
	}
}
