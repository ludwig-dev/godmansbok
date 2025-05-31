package org.ludwig.godmansbok.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ludwig.godmansbok.domain.godman.Godman;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    public Godman toGodman() {
        Godman godman = new Godman();
        godman.setUsername(username);
        godman.setEmail(email);
        godman.setPassword(password);
        return godman;
    }
}
