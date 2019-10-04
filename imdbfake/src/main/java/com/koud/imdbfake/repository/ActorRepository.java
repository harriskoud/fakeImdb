package com.koud.imdbfake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koud.imdbfake.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository <Actor, Integer> {

}
