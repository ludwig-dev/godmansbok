package org.ludwig.godmansbok.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{
    List<Account> findAllByClientId(Long clientId);
    Optional<Account> findFirstByClientId(Long clientId);
}
