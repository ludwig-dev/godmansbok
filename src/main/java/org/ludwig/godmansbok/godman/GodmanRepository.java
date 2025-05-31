package org.ludwig.godmansbok.godman;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GodmanRepository extends JpaRepository<Godman, Long> {
    Optional<Godman> findByUsernameIgnoreCase(String username);
    Optional<Godman> findByEmailIgnoreCase(String email);
    List<Godman> findByUsernameContainingIgnoreCase(String username);
}
