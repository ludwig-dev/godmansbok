package org.ludwig.godmansbok.domain.liability;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiabilityRepository extends JpaRepository<Liability, Long> {
    List<Liability> findAllByClientId(Long clientId);
}
