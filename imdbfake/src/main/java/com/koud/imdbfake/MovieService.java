package com.koud.imdbfake;

import org.springframework.stereotype.Service;

import com.koud.imdbfake.model.Movie;

@Service
public class MovieService {

	public Movie likeMovie(Movie movie, boolean like) {
		int likes = movie.getLikes();
		if(like) {
			movie.setLikes(++likes);
		}else {
			movie.setLikes(--likes);
		}
		return movie;
	}
}
