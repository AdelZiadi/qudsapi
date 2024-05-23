package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.VillageLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.VillageLocation}.
 */
public interface VillageLocationService {
    /**
     * Save a villageLocation.
     *
     * @param villageLocation the entity to save.
     * @return the persisted entity.
     */
    VillageLocation save(VillageLocation villageLocation);

    /**
     * Updates a villageLocation.
     *
     * @param villageLocation the entity to update.
     * @return the persisted entity.
     */
    VillageLocation update(VillageLocation villageLocation);

    /**
     * Partially updates a villageLocation.
     *
     * @param villageLocation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VillageLocation> partialUpdate(VillageLocation villageLocation);

    /**
     * Get all the villageLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VillageLocation> findAll(Pageable pageable);

    /**
     * Get the "id" villageLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VillageLocation> findOne(Long id);

    /**
     * Delete the "id" villageLocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
