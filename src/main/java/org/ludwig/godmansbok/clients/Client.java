package org.ludwig.godmansbok.clients;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}

