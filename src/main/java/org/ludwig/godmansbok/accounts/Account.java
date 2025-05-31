package org.ludwig.godmansbok.accounts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}
