package org.ludwig.godmansbok.auth;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.ludwig.godmansbok.auth.dto.LoginRequest;
import org.ludwig.godmansbok.auth.dto.RegisterRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest req) {

        authService.register(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Godman registered successfully");
    }
}
