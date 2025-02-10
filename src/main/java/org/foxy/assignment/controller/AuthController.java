package org.foxy.assignment.controller;

import org.foxy.assignment.dto.AuthRequest;
import org.foxy.assignment.dto.AuthResponse;
import org.foxy.assignment.security.JwtUtil;
import org.foxy.assignment.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
