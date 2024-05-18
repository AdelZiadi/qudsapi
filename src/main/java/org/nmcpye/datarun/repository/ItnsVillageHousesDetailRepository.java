package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ItnsVillageHousesDetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItnsVillageHousesDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItnsVillageHousesDetailRepository extends JpaRepository<ItnsVillageHousesDetail, Long> {}
