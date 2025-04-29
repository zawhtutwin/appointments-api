package com.clinic.app.controllers.apis.dtos;

import java.time.Instant;

public class AuthResponse {
	String apiKey;
    Instant expiresAt;
    
	public AuthResponse(String apiKey, Instant expiresAt) {
		super();
		this.apiKey = apiKey;
		this.expiresAt = expiresAt;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public Instant getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
    
    
}
