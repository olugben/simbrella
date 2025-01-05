package com.hammed.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Service to validate and parse the JWT

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;


    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Extract the Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if the header is valid
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Proceed without authentication
            return;
        }

        // Extract JWT from the header
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt); // Extract username (email) from JWT

        // Authenticate the user if the JWT is valid and user is not authenticated already
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Validate the JWT
            if (jwtService.isTokenValid(jwt)) {
                // Create an authentication token with the user information from JWT
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userEmail, null, null); // You can add authorities if you need

                // Set the details of the request for further use (e.g., logging, access control)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Proceed with the filter chain (pass the request to the next filter)
        filterChain.doFilter(request, response);
    }
}
