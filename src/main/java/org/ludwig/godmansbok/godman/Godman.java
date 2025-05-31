package org.ludwig.godmansbok.godman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ludwig.godmansbok.clients.Client;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "godmen")
public class Godman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;       // Ex. "ROLE_USER" eller "ROLE_ADMIN"

    // Relation: En godman kan ha flera klienter (huvudmän)
    @OneToMany(
            mappedBy = "godman",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Client> clients;
}
