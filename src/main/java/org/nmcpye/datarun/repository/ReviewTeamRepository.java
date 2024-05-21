package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.ReviewTeam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReviewTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewTeamRepository extends JpaRepository<ReviewTeam, Long> {}
