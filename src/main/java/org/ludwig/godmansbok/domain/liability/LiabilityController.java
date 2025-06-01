package org.ludwig.godmansbok.domain.liability;

import jakarta.validation.Valid;
import org.ludwig.godmansbok.domain.liability.dto.LiabilityDTO;
import org.ludwig.godmansbok.domain.liability.dto.LiabilityUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<LiabilityDTO> getAllLiabilities(
            @PathVariable Long clientId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return LiabilityDTO.toDtos(liabilityService.getAllLiabilities(godmanId, clientId));
    }

    @GetMapping("/{liabilityId}")
    public LiabilityDTO getLiabilityById(
            @PathVariable Long clientId,
            @PathVariable Long liabilityId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return LiabilityDTO.toDto(liabilityService.getLiabilityById(godmanId, clientId, liabilityId));
    }

    @PatchMapping("/{liabilityId}")
    public LiabilityDTO updateLiability(
            @PathVariable Long clientId,
            @PathVariable Long liabilityId,
            @Valid @RequestBody LiabilityUpdateDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return LiabilityDTO.toDto(liabilityService.updateLiability(godmanId, clientId, liabilityId, dto));
    }
}
