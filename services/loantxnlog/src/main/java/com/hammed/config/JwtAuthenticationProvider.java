package com.hammed.config;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
//
//@Component
//public class JwtAuthenticationProvider implements AuthenticationProvider {
//
//    private final JwtService jwtService;
//
//    public JwtAuthenticationProvider(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        String username = (String) authentication.getPrincipal();
//        String jwt = (String) authentication.getCredentials();
//
//        if (jwtService.isTokenValid(jwt)) {
//            // Assuming the token is valid, create an authenticated user token with role "USER"
//            return new UsernamePasswordAuthenticationToken(
//                    new User(username, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))),
//                    jwt, // credentials
//                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//        }
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}

