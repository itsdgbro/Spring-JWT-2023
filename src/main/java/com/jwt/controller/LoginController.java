package com.jwt.controller;

import com.jwt.config.JwtService;
import com.jwt.error.CustomErrorResponse;
import com.jwt.model.JwtRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final CustomErrorResponse customErrorResponse;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody(required = false) JwtRequest request) {

        if (request == null ||
                (request.getUsername() == null || request.getUsername().isEmpty()) ||
                (request.getPassword() == null || request.getPassword().isEmpty())) {

            customErrorResponse.setError("Bad Request");
            customErrorResponse.setMessage("Request body is empty");
            return ResponseEntity.status(400).body(customErrorResponse);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);

        if (jwtToken != null && !jwtToken.isEmpty()) {
            Map<String , String> response = new HashMap<>();
            response.put("token", jwtToken);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body("Failed to generate JWT token");
        }
    }

}