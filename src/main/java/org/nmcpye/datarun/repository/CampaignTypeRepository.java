package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.CampaignType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CampaignType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignTypeRepository extends JpaRepository<CampaignType, Long> {}
