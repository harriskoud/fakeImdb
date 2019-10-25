package com.koud.imdbfake;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.koud.imdbfake.model.Actor;
import com.koud.imdbfake.model.Movie;
import com.koud.imdbfake.model.User;
import com.koud.imdbfake.repository.ActorRepository;
import com.koud.imdbfake.repository.MovieRepository;
import com.koud.imdbfake.repository.UserRepository;

@SpringBootApplication
@EnableResourceServer
public class ImdbfakeApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImdbfakeApplication.class, args);
	}

	@Bean
	public User testUser() {

		User user = new User();
		user.setCreationDate(new Date());
		user.setEmail("hkoudoumas@gmail.com");
		user.setUsername("harris");
		user.setPassword("12345");
		user.setLocked(true);
		user.setUserId(1);
		userRepository.save(user);
		return user;
	}

	@Bean
	public Actor testActor() {

		Actor actor = new Actor();
		Movie movie = Movie.builder().name("Movie1").movieId(1).director("Director1").published(new Date()).likes(0).build();
		movieRepository.save(movie);

		actor.setActorId(1);
		actorRepository.save(actor);
		return actor;
	}

}
