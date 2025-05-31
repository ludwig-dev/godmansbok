package org.ludwig.godmansbok.accounts.repositories;

import org.ludwig.godmansbok.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}