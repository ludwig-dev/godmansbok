package org.ludwig.godmansbok.domain.godman;

import org.ludwig.godmansbok.domain.godman.dto.GodmanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/godman")
public class GodmanController {
    private final GodmanService godmanService;

    public GodmanController(GodmanService godmanService) {
        this.godmanService = godmanService;
    }

    @GetMapping("/me")
    public GodmanDTO getCurrentUser(Authentication authentication) {
        Long id = requireAuth(authentication);
        return GodmanDTO.toDto(godmanService.getGodman(id));
    }

    private Long requireAuth(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return Long.parseLong(auth.getName());
    }
}
