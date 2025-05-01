package com.clinic.app.controllers.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.app.dto.Me;

@RestController
@RequestMapping("/api/me")
public class PeronalApiController {
	
	@GetMapping
	public ResponseEntity<Me> me(Authentication auth) {
		return  ResponseEntity.ok(new Me((String)auth.getPrincipal()));
	}
}
