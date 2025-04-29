package com.clinic.app.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.clinic.app.models.ApiKey;
import com.clinic.app.repos.ApiKeyRepository;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public boolean isValidApiKey(String apiKey) {
    	
        return apiKeyRepository.findByKey(apiKey).isPresent();
    }

    public String generateApiKeyForUser(String email) {
        String generatedKey = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plusSeconds(3600); // 1 hour

        ApiKey apiKey = new ApiKey(generatedKey, email, expiresAt);
        apiKeyRepository.save(apiKey);

        return generatedKey;
    }
    
    public void invalidateApiKey(String apiKey) {
        apiKeyRepository.deleteById(apiKey);
    }
}
