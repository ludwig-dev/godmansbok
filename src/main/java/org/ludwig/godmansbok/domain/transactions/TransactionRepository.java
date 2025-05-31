package org.ludwig.godmansbok.domain.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountId(Long accountId);
}


// Från GPT
//@Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
//        "WHERE t.account.client.id = :clientId " +
//        "AND t.type = :type " +
//        "AND t.date BETWEEN :start AND :end")
//BigDecimal sumByClientAndTypeBetween(
//        @Param("clientId") Long clientId,
//        @Param("type") String type,
//        @Param("start") LocalDate start,
//        @Param("end") LocalDate end);