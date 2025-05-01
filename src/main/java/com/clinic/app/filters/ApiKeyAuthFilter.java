package com.clinic.app.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clinic.app.controllers.apis.dtos.ErrorResponse;
import com.clinic.app.models.ApiKey;
import com.clinic.app.repos.ApiKeyRepository;
import com.clinic.app.services.ApiKeyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER_NAME = "X-API-KEY";

    @Autowired
    ApiKeyService apiKeyService;
 
    @Autowired
    ApiKeyRepository apiKeyRepository;
    
    static String BEARER_PREFIX = "Bearer ";
    static String INVALID_AUTH_HEADER = "Forbidden: Missing or invalid Authorization header";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // Skip auth endpoint
        if (path.equals("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
				String authHeader = request.getHeader("Authorization");
				
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					throw new RuntimeException(INVALID_AUTH_HEADER);
				}
				   
			    String token = authHeader.substring(BEARER_PREFIX.length());
			    ApiKey apiKey = apiKeyRepository.findByKey(token).orElseThrow(()->new RuntimeException(INVALID_AUTH_HEADER));
			    
			    UsernamePasswordAuthenticationToken authentication =
				    new UsernamePasswordAuthenticationToken(
				        apiKey.getEmail(),
				        null,
				        List.of("ROLE_USER").stream().map(SimpleGrantedAuthority::new).toList()
				    );
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
            
        }catch(RuntimeException e) {
        	ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(objectMapper.writeValueAsString(new ErrorResponse(e.getMessage())));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
