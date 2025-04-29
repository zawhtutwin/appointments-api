package com.clinic.app.controllers.apis;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.app.controllers.apis.dtos.AuthRequest;
import com.clinic.app.controllers.apis.dtos.AuthResponse;
import com.clinic.app.models.ApiKey;
import com.clinic.app.models.User;
import com.clinic.app.repos.ApiKeyRepository;
import com.clinic.app.repos.UserRepository;
import com.clinic.app.services.ApiKeyService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	ApiKeyService apiKeyService;
	
    @Autowired
    ApiKeyRepository apiKeyRepository;	
	
    @Autowired
    private UserRepository userRepository;

    @Autowired    
    private PasswordEncoder passwordEncoder;
    
    
    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        // Fetch user from DB
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate API key
        String apiKey = apiKeyService.generateApiKeyForUser(email);
        Instant expiresAt = Instant.now().plusSeconds(3600);

        // Store API key in DB
        ApiKey key = new ApiKey(apiKey, email, expiresAt);
        apiKeyRepository.save(key);

        return ResponseEntity.ok(new AuthResponse(apiKey, expiresAt));
    }

}
