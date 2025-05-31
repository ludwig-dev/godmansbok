package org.ludwig.godmansbok.domain.clients;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ludwig.godmansbok.domain.account.Account;
import org.ludwig.godmansbok.domain.liability.Liability;
import org.ludwig.godmansbok.domain.otherasset.OtherAsset;
import org.ludwig.godmansbok.domain.godman.Godman;

import java.util.List;

// HUVUDMAN
@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Namn på huvudmannen
    @Column(nullable = false)
    private String name;

    // Personnummer
    @Column(nullable = true, length = 12)
    private String personalNumber;

    // Relation: Många Clients till en Godman
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "godman_id", nullable = false)
    private Godman godman;

    // Relationer: En huvudman kan ha flera konton, tillgångar och skulder
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Account> accounts;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OtherAsset> otherAssets;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Liability> liabilities;
}

