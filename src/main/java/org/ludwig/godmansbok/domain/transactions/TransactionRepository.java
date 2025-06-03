package org.ludwig.godmansbok.domain.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountId(Long accountId);

    /**
     * Summera alla belopp (amount) för transaktioner av typen “INKOMST” för det givna kontot,
     * där transaktionsdatum ligger mellan given start‐ och slutdatum (inklusiva).
     * <p>
     * Om inga transaktioner finns returnerar summan NULL → vi hanterar det i servicen.
     */
    @Query("""
              SELECT COALESCE(SUM(t.amount), 0)
              FROM Transaction t
              WHERE t.account.id = :accountId
                AND t.type = 'INKOMST'
                AND t.date BETWEEN :fromDate AND :toDate
            """)
    BigDecimal sumIncomesForAccountAndPeriod(
            @Param("accountId") Long accountId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );

    @Query("""
              SELECT COALESCE(SUM(t.amount), 0)
              FROM Transaction t
              WHERE t.account.id = :accountId
                AND t.type = 'UTGIFT'
                AND t.date BETWEEN :fromDate AND :toDate
            """)
    BigDecimal sumExpensesForAccountAndPeriod(
            @Param("accountId") Long accountId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
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