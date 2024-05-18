package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.MVillagesLocations;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MVillagesLocations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MVillagesLocationsRepository extends JpaRepository<MVillagesLocations, Long> {}
