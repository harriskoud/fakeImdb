package com.koud.imdbfake.resource;

import java.util.List;
import java.util.Optional;

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
import com.koud.imdbfake.repository.MovieRepository;

@RestController
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieResource {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieService movieService;

	@GetMapping
	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getMoviesById(@PathVariable int id) {

		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent())
			return ResponseEntity.ok(movie.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public void postMovie(@RequestBody Movie movie) {
		movieRepository.save(movie);
	}

	@GetMapping("like/{id}")
	public @ResponseBody ResponseEntity<?> voteForMovie(@PathVariable int id,
			@RequestParam(required = true) boolean like) {

		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			Movie updatedMovie = movieRepository.save(movieService.likeMovie(movie.get(), like));
			return ResponseEntity.ok(updatedMovie);
		} else
			return ResponseEntity.notFound().build();

	}

}
