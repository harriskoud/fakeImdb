package com.koud.imdbfake.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_liked_movies")
@IdClass(UserMovieRelationship.class)
public class UserLikedMovies{
	
	@Id
	private int userId;
	@Id
	private int movieId;
	private int favour;

}
