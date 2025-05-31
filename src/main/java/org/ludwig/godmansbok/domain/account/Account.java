package org.ludwig.godmansbok.domain.account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ludwig.godmansbok.domain.transactions.Transaction;
import org.ludwig.godmansbok.domain.clients.Client;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Exempelvis "Bankkonto 1", "Fickpengskonto", etc.
    @Column(nullable = false)
    private String accountName;

    @Column(nullable = true)
    private String accountNumber;

    // Relation: Varje konto hör till en och samma klient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Relation: Transaktioner (inkomst/utgift) på detta konto
    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;

}
