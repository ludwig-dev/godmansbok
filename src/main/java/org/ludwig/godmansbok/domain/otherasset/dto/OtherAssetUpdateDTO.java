package org.ludwig.godmansbok.domain.otherasset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherAssetUpdateDTO {
    private String assetType;
    private String description;
    private BigDecimal units;
    private BigDecimal valueStartOfYear;
    private BigDecimal valueEndOfYear;
    private String attachmentNumber;
}
