package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillageHousesDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ItnsVillageHousesDetail}.
 */
public interface ItnsVillageHousesDetailService {
    /**
     * Save a itnsVillageHousesDetail.
     *
     * @param itnsVillageHousesDetail the entity to save.
     * @return the persisted entity.
     */
    ItnsVillageHousesDetail save(ItnsVillageHousesDetail itnsVillageHousesDetail);

    /**
     * Updates a itnsVillageHousesDetail.
     *
     * @param itnsVillageHousesDetail the entity to update.
     * @return the persisted entity.
     */
    ItnsVillageHousesDetail update(ItnsVillageHousesDetail itnsVillageHousesDetail);

    /**
     * Partially updates a itnsVillageHousesDetail.
     *
     * @param itnsVillageHousesDetail the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItnsVillageHousesDetail> partialUpdate(ItnsVillageHousesDetail itnsVillageHousesDetail);

    /**
     * Get all the itnsVillageHousesDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItnsVillageHousesDetail> findAll(Pageable pageable);

    /**
     * Get the "id" itnsVillageHousesDetail.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItnsVillageHousesDetail> findOne(Long id);

    /**
     * Delete the "id" itnsVillageHousesDetail.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
