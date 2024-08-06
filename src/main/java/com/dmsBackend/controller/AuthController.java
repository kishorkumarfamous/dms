package com.dmsBackend.controller;

import com.dmsBackend.repository.EmployeeRepository;
import com.dmsBackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // Load user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails.getUsername());

            // Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    public static class AuthRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setUsername(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthResponse {
        private String token;

        private Integer Id;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
