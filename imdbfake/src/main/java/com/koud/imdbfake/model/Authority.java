package com.koud.imdbfake.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="authorities")
public class Authority implements Serializable {
	
	
	private static final int serialVersionUID = 1;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authId;
	private int userId;
	private String authRole;
	private String userName;
}
