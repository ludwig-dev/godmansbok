package org.ludwig.godmansbok.domain.otherasset.dto;

import org.ludwig.godmansbok.domain.otherasset.OtherAsset;

import java.math.BigDecimal;
import java.util.List;

public record OtherAssetDTO(Long id, String assetType, String description, BigDecimal units,
                            BigDecimal valueStartOfYear, BigDecimal valueEndOfYear, String attachmentNumber) {
    public static OtherAssetDTO toDto(OtherAsset otherAsset) {
        return new OtherAssetDTO(otherAsset.getId(), otherAsset.getAssetType(), otherAsset.getDescription(), otherAsset.getUnits(), otherAsset.getValueStartOfYear(), otherAsset.getValueEndOfYear(), otherAsset.getAttachmentNumber());
    }
    public static List<OtherAssetDTO> toDtos(List<OtherAsset> otherAssets) {
        return otherAssets.stream().map(OtherAssetDTO::toDto).toList();
    }
}
