package org.ludwig.godmansbok.accounts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    // Belopp i kronor (ex. 1000.00)
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    // "INKOMST" eller "UTGIFT"
    @Column(nullable = false, length = 10)
    private String type;

    // Valfri beskrivning/konteringstext
    @Column(nullable = true)
    private String description;

    // Bilagereferens (fritt fält)
    @Column(nullable = true, length = 50)
    private String attachmentNumber;

    // ÖF-notering (fritt fält för granskning)
    @Column(nullable = true, length = 200)
    private String ofNotation;

}
