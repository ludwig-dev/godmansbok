package org.ludwig.godmansbok.domain.transactions.dto;

import org.ludwig.godmansbok.domain.transactions.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TransactionDTO(Long id, LocalDate date, BigDecimal amount, String type, String description, String attachmentNumber) {
    public static TransactionDTO toDto(Transaction transaction) {
        return new TransactionDTO(transaction.getId(), transaction.getDate(), transaction.getAmount(), transaction.getType(), transaction.getDescription(), transaction.getAttachmentNumber());
    }

    public static List<TransactionDTO> toDtos(List<Transaction> transactions) {
        return transactions.stream().map(TransactionDTO::toDto).toList();
    }
}
