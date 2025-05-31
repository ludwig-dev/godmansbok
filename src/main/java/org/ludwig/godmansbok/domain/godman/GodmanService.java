package org.ludwig.godmansbok.domain.godman;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void registerNewUser(Godman godman) {
        godman.setRole("GODMAN");
        godman.setEmail(godman.getEmail().toLowerCase());
        godman.setUsername(godman.getUsername().toLowerCase());
        godman.setPassword(passwordEncoder.encode(godman.getPassword()));
        godmanRepository.save(godman);
    }

    public Godman getGodman(Long godmanId) {
        return godmanRepository.findById(godmanId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Godman not found"));
    }
}
