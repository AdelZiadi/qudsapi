package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.TeamFormAccess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TeamFormAccess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeamFormAccessRepository extends JpaRepository<TeamFormAccess, Long> {}
