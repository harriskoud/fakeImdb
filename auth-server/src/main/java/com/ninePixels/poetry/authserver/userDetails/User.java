package com.ninePixels.poetry.authserver.userDetails;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int userId;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	private String email;
	@NonNull
	private Date creationDate;
	private boolean isLocked;
	private int timesBeforeLocked;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public int getTimesBeforeLocked() {
		return timesBeforeLocked;
	}

	public void setTimesBeforeLocked(int timesBeforeLocked) {
		this.timesBeforeLocked = timesBeforeLocked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
