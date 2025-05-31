package org.ludwig.godmansbok.domain.otherasset;

import jakarta.validation.Valid;
import org.ludwig.godmansbok.domain.otherasset.dto.OtherAssetDTO;
import org.ludwig.godmansbok.domain.otherasset.dto.OtherAssetUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/{clientId}/other-assets")
public class OtherAssetController {

    private final OtherAssetService otherAssetService;

    public OtherAssetController(OtherAssetService otherAssetService) {
        this.otherAssetService = otherAssetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OtherAssetDTO createOtherAsset(
            @PathVariable Long clientId,
            @Valid @RequestBody OtherAssetDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return OtherAssetDTO.toDto(otherAssetService.createOtherAsset(godmanId, clientId, dto));
    }

    @GetMapping
    public List<OtherAssetDTO> getAllOtherAssets(
            @PathVariable Long clientId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return OtherAssetDTO.toDtos(otherAssetService.getAllOtherAssets(godmanId, clientId));
    }

    @GetMapping("/{otherAssetId}")
    public OtherAssetDTO getOtherAssetById(
            @PathVariable Long clientId,
            @PathVariable Long otherAssetId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return OtherAssetDTO.toDto(otherAssetService.getOtherAssetById(godmanId, clientId, otherAssetId));
    }

    @PatchMapping("/{otherAssetId}")
    public OtherAssetDTO updateOtherAsset(
            @PathVariable Long clientId,
            @PathVariable Long otherAssetId,
            @Valid @RequestBody OtherAssetUpdateDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return OtherAssetDTO.toDto(otherAssetService.updateOtherAsset(godmanId, clientId, otherAssetId, dto));
    }

    @DeleteMapping("/{otherAssetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOtherAsset(
            @PathVariable Long clientId,
            @PathVariable Long otherAssetId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        otherAssetService.deleteOtherAsset(godmanId, clientId, otherAssetId);
    }
}
