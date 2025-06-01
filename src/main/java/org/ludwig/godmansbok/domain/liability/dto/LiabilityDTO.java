package org.ludwig.godmansbok.domain.liability.dto;

import org.ludwig.godmansbok.domain.liability.Liability;

import java.math.BigDecimal;
import java.util.List;

public record LiabilityDTO(Long id, String creditor, BigDecimal debtStartOfYear,
                           BigDecimal debtEndOfYear,
                           BigDecimal changeAmount, String attachmentNumber) {
    public static LiabilityDTO toDto(Liability liability) {
        return new LiabilityDTO(liability.getId(), liability.getCreditor(), liability.getDebtStartOfYear(), liability.getDebtEndOfYear(),
                liability.getChangeAmount(), liability.getAttachmentNumber());
    }

    public static List<LiabilityDTO> toDtos(List<Liability> liabilities) {
        return liabilities.stream().map(LiabilityDTO::toDto).toList();
    }
}