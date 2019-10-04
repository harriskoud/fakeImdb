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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koud.imdbfake.model.Actor;
import com.koud.imdbfake.repository.ActorRepository;

@RestController
@RequestMapping(value = "/actor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActorResource {

	@Autowired
	private ActorRepository actorRepository;

	@GetMapping
	public List<Actor> getActors() {
		return actorRepository.findAll();
	}

	@GetMapping("/id")
	public @ResponseBody ResponseEntity<?> getActorsById(@PathVariable int id) {

		Optional<Actor> actor = actorRepository.findById(id);
		if (actor.isPresent())
			return ResponseEntity.ok(actor.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public void postActor(@RequestBody Actor actor) {
		actorRepository.save(actor);
	}

}
