package org.ludwig.godmansbok.auth;

import org.ludwig.godmansbok.auth.dto.LoginRequest;
import org.ludwig.godmansbok.auth.dto.RegisterRequest;
import org.ludwig.godmansbok.godman.Godman;
import org.ludwig.godmansbok.godman.GodmanService;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final GodmanService godmanService;
    private final PasswordEncoder encoder;

    public AuthService(JwtUtil jwtUtil, GodmanService godmanService, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.godmanService = godmanService;
        this.encoder = passwordEncoder;
    }

    public ResponseCookie login(LoginRequest req) {
        Godman u = godmanService.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials. Invalid email or password."));
        if (!encoder.matches(req.getPassword(), u.getPassword()))
            throw new BadCredentialsException("Bad credentials. Invalid email or password.");

        String token = jwtUtil.generateToken(u);
        return ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();
    }

    public ResponseCookie createLogoutCookie() {
        return ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
    }

    public void register(RegisterRequest req) {
        if (godmanService.findByUsername(req.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (godmanService.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        Godman u = req.toGodman();
        godmanService.registerNewUser(u);
    }
}
