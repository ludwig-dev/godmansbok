package org.ludwig.godmansbok.domain.transactions.dto;

import org.ludwig.godmansbok.domain.transactions.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDTO(Long id, LocalDate date, BigDecimal amount, String type, String description, String attachmentNumber) {
    public static TransactionDTO toDto(Transaction transaction) {
        return new TransactionDTO(transaction.getId(), transaction.getDate(), transaction.getAmount(), transaction.getType(), transaction.getDescription(), transaction.getAttachmentNumber());
    }
}
