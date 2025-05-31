package org.ludwig.godmansbok.domain.transactions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ludwig.godmansbok.domain.account.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datum för transaktionen
    @Column(nullable = false)
    private LocalDate date;

    // Belopp i kronor
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    // "INKOMST" eller "UTGIFT"
    @Column(nullable = false, length = 10)
    private String type;

    // Valfri beskrivning/konteringstext
    @Column(nullable = true)
    private String description;

    // Bilagereferens
    @Column(nullable = true, length = 50)
    private String attachmentNumber;

    // Relation: Varje transaktion tillhör exakt ett konto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
