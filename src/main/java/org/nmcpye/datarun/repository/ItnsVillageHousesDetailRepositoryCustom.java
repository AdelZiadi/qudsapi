package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ItnsVillageHousesDetail;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the ItnsVillageHousesDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItnsVillageHousesDetailRepositoryCustom
    extends ItnsVillageHousesDetailRepository {

    Optional<ItnsVillageHousesDetail> findByUid(String uid);
}
