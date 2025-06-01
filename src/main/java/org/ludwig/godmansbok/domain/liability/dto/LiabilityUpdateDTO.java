package org.ludwig.godmansbok.domain.liability.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiabilityUpdateDTO {
    private String creditor;
    private BigDecimal debtStartOfYear;
    private BigDecimal debtEndOfYear;
    private BigDecimal changeAmount;
    private String attachmentNumber;
}
