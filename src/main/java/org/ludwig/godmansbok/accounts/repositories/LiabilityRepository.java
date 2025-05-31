package org.ludwig.godmansbok.accounts.repositories;

import org.ludwig.godmansbok.accounts.Liability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiabilityRepository extends JpaRepository<Liability, Long> {
}
