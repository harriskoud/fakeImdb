package com.koud.imdbfake.resource;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koud.imdbfake.MovieService;
import com.koud.imdbfake.model.Movie;
import com.koud.imdbfake.model.User;
import com.koud.imdbfake.model.UserLikedMovies;
import com.koud.imdbfake.repository.MovieRepository;
import com.koud.imdbfake.repository.UserLikedMovieRepository;
import com.koud.imdbfake.repository.UserRepository;

@RestController
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieResource {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserLikedMovieRepository userLikedMovieRepository;
	@Autowired
	private MovieService movieService;

	@GetMapping
	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}

	@GetMapping("/{id}/user/{userId}")
	public @ResponseBody ResponseEntity<?> getMoviesById(@PathVariable int id, @PathVariable int userId) {

		Optional<Movie> movie = movieRepository.findById(id);
		Optional<User> user = userRepository.findById(userId);
		Optional<UserLikedMovies> userLikedMovie = userLikedMovieRepository.getRelationship( userId, id);
		if (movie.isPresent() && user.isPresent()) {
			JSONObject response = new JSONObject();
			response.put("movie", new JSONObject(movie.get()));

			if (userLikedMovie.isPresent())
				response.put("userFavour", new JSONObject(userLikedMovie.get()));
			else
				response.put("userFavour", "");
			return ResponseEntity.ok(response.toString());
		} else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public void postMovie(@RequestBody Movie movie) {
		movieRepository.save(movie);
	}

	@GetMapping("like/{id}")
	public @ResponseBody ResponseEntity<?> voteForMovie(@PathVariable int id,
			@RequestParam(value = "like", required = true) boolean like, @RequestParam(value = "userId",required = true) int userId) {

		Optional<Movie> movie = movieRepository.findById(id);
		Optional<User> user = userRepository.findById(userId);

		if (movie.isPresent() && user.isPresent()) {
			Movie updatedMovie = movieRepository.save(movieService.likeMovie(movie.get(), like, userId));
			return ResponseEntity.ok(updatedMovie);
		} else
			return ResponseEntity.notFound().build();

	}

}
