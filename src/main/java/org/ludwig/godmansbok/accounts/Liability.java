package org.ludwig.godmansbok.accounts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "liabilities")
public class Liability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Namn på långivare (eller fordringsägare)
    @Column(nullable = false)
    private String creditor;

    // Skuld per 1 januari
    @Column(name = "debt_start_of_year", nullable = false, precision = 14, scale = 2)
    private BigDecimal debtStartOfYear;

    // Skuld per 31 december
    @Column(name = "debt_end_of_year", nullable = false, precision = 14, scale = 2)
    private BigDecimal debtEndOfYear;

    // Förändring (kan också räknas ut hos frontend/backend, men här sparas som fält)
    @Column(nullable = true, precision = 14, scale = 2)
    private BigDecimal changeAmount;

    // Bilaga nr (fritt fält)
    @Column(nullable = true, length = 50)
    private String attachmentNumber;
}
