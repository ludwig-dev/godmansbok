package org.ludwig.godmansbok.accounts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ludwig.godmansbok.clients.Client;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "other_assets")
public class OtherAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Typ av övrig tillgång (t.ex. "Värdepapper", "Fastighet", "Fonder" etc.)
    @Column(nullable = false, length = 50)
    private String assetType;

    // Beskrivande fält (t.ex. "ABC Fonder", "Bostadsrätt i Stockholm", etc.)
    @Column(nullable = true)
    private String description;

    // Antal andelar (om det är ett värdepapper, fond, etc.)
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal units;

    // Värde per 1 januari (sek)
    @Column(name = "value_start_of_year", nullable = false, precision = 14, scale = 2)
    private BigDecimal valueStartOfYear;

    // Värde per 31 december (sek)
    @Column(name = "value_end_of_year", nullable = false, precision = 14, scale = 2)
    private BigDecimal valueEndOfYear;

    // Bilaga nr (fritt fält)
    @Column(nullable = true, length = 50)
    private String attachmentNumber;

    // Relation: Varje övrig tillgång är kopplad till en klient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
