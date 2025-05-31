package org.ludwig.godmansbok.domain.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String type;           // "INKOMST" eller "UTGIFT"
    private String description;
    private String attachmentNumber;
}
