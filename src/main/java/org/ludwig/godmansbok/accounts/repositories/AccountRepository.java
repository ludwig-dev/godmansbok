package org.ludwig.godmansbok.accounts.repositories;

import org.ludwig.godmansbok.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account, Long>{
}
