package org.ludwig.godmansbok.domain.liability;

import jakarta.validation.Valid;
import org.ludwig.godmansbok.domain.liability.dto.LiabilityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/{clientId}/liabilities")
public class LiabilityController {
    private final LiabilityService liabilityService;

    public LiabilityController(LiabilityService liabilityService) {
        this.liabilityService = liabilityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LiabilityDTO createLiability(
            @PathVariable Long clientId,
            @Valid @RequestBody LiabilityDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return LiabilityDTO.toDto(liabilityService.createLiability(godmanId, clientId, dto));
    }
}
