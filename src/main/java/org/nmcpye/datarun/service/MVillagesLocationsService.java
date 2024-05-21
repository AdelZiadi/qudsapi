package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.MVillagesLocations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.MVillagesLocations}.
 */
public interface MVillagesLocationsService {
    /**
     * Save a mVillagesLocations.
     *
     * @param mVillagesLocations the entity to save.
     * @return the persisted entity.
     */
    MVillagesLocations save(MVillagesLocations mVillagesLocations);

    /**
     * Updates a mVillagesLocations.
     *
     * @param mVillagesLocations the entity to update.
     * @return the persisted entity.
     */
    MVillagesLocations update(MVillagesLocations mVillagesLocations);

    /**
     * Partially updates a mVillagesLocations.
     *
     * @param mVillagesLocations the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MVillagesLocations> partialUpdate(MVillagesLocations mVillagesLocations);

    /**
     * Get all the mVillagesLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MVillagesLocations> findAll(Pageable pageable);

    /**
     * Get the "id" mVillagesLocations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MVillagesLocations> findOne(Long id);

    /**
     * Delete the "id" mVillagesLocations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
