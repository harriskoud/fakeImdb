package com.koud.imdbfake.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "actors")
public class Actor implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	private int actorId;
	private String actorName;
	private Date birthDate;
}
