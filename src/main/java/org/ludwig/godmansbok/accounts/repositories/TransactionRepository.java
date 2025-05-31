package org.ludwig.godmansbok.accounts.repositories;

import org.ludwig.godmansbok.accounts.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
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