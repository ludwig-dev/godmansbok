package org.ludwig.godmansbok.godman;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GodmanService {

    private final GodmanRepository godmanRepository;
    private final PasswordEncoder passwordEncoder;

    public GodmanService(GodmanRepository godmanRepository, PasswordEncoder passwordEncoder) {
        this.godmanRepository = godmanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Godman> findByUsername(String username) {
        return godmanRepository.findByUsernameIgnoreCase(username);
    }

    public Optional<Godman> findByEmail(String email) {
        return godmanRepository.findByEmailIgnoreCase(email);
    }
}
