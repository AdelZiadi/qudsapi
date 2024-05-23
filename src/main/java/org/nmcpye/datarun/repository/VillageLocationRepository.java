package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.VillageLocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VillageLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VillageLocationRepository extends JpaRepository<VillageLocation, Long> {}
