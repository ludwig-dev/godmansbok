package org.ludwig.godmansbok.domain.godman.dto;

import org.ludwig.godmansbok.domain.godman.Godman;

public record GodmanDTO(Long id, String username, String email, String role) {
    public static GodmanDTO toDto(Godman godman) {
        return new GodmanDTO(godman.getId(), godman.getUsername(), godman.getEmail(), godman.getRole());
    }
}
