package com.hammed.auth;


import com.hammed.UserManagement.UserRepository;
import com.hammed.auth.AuthenticateRequest;
import com.hammed.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.hammed.config.JwtService;


import com.hammed.UserManagement.User;
import com.hammed.UserManagement.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

private final UserRepository repository;
//    @Autowired
//    public UserService(UserRepository repository){
//        this.repository = repository;
//
//    }
private final PasswordEncoder  passwordEncoder;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;


@Autowired
    public AuthenticationService(JwtService jwtService, UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;

    }
    public com.hammed.auth.AuthenticationResponse register(com.hammed.auth.RegisterRequest request) {

    var user =User.builder()
                            .firstname(request.getFirstname())
                            .lastname(request.getLastname())
                            .email(request.getEmail())
                            .phone_number(request.getPhone_number())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .role(request.getRole())
                            .build();
        repository.save(user);
        var jwtToken =jwtService.generateToken(user);

          return com.hammed.auth.AuthenticationResponse.builder().token(jwtToken).build();

    }

    public com.hammed.auth.AuthenticationResponse authenticate(AuthenticateRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {}", request.getEmail(), e);
            throw e;

        }
       var user= repository.findByEmail(request.getEmail()).orElseThrow();
       var jwtToken =jwtService.generateToken(user);

        return com.hammed.auth.AuthenticationResponse.builder().token(jwtToken).build();
    }

}




































//package com.hammed.auth;
//
//import com.hammed.UserManagement.User;
//import com.hammed.UserManagement.UserRepository;
//import com.hammed.config.JwtService;
//
//import lombok.RequiredArgsConstructor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticateRequest request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
//        } catch (Exception e) {
//            logger.error("Authentication failed for user: {}", request.getEmail(), e);
//            throw e;
//        }
//
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}
