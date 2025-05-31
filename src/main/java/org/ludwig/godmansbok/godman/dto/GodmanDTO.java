package org.ludwig.godmansbok.godman.dto;

import org.ludwig.godmansbok.godman.Godman;

public record GodmanDTO(Long id, String username, String email, String role) {
    public static GodmanDTO toDto(Godman godman) {
        return new GodmanDTO(godman.getId(), godman.getUsername(), godman.getEmail(), godman.getRole());
    }
}

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class GodmanDTO {
//    private Long id;
//    private String username;
//    private String email;
//    private String role;
//}
