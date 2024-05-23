package org.nmcpye.datarun.service;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.CampaignType;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.CampaignType}.
 */
public interface CampaignTypeService {
    /**
     * Save a campaignType.
     *
     * @param campaignType the entity to save.
     * @return the persisted entity.
     */
    CampaignType save(CampaignType campaignType);

    /**
     * Updates a campaignType.
     *
     * @param campaignType the entity to update.
     * @return the persisted entity.
     */
    CampaignType update(CampaignType campaignType);

    /**
     * Partially updates a campaignType.
     *
     * @param campaignType the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CampaignType> partialUpdate(CampaignType campaignType);

    /**
     * Get all the campaignTypes.
     *
     * @return the list of entities.
     */
    List<CampaignType> findAll();

    /**
     * Get the "id" campaignType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignType> findOne(Long id);

    /**
     * Delete the "id" campaignType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
