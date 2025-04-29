package com.clinic.app.models;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ApiKey {
	@Id
    private String key; // Use the UUID as ID

    private String email;

    private Instant expiresAt;
    public ApiKey() {
    	
    }
	public ApiKey(String key, String email, Instant expiresAt) {
		super();
		this.key = key;
		this.email = email;
		this.expiresAt = expiresAt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}



}
