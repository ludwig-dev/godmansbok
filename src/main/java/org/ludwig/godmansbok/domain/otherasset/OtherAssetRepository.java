package org.ludwig.godmansbok.domain.otherasset;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherAssetRepository extends JpaRepository<OtherAsset, Long> {
    List<OtherAsset> findAllByClientId(Long clientId);
}