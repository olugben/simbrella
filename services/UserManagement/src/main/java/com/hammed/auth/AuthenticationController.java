package com.hammed.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final com.hammed.auth.AuthenticationService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @PostMapping("/register")
    public ResponseEntity<com.hammed.auth.AuthenticationResponse> register(@RequestBody com.hammed.auth.RegisterRequest request) {
        logger.error("j");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<com.hammed.auth.AuthenticationResponse> register(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
