package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ItnsVillage}.
 */
public interface ItnsVillageService {
    /**
     * Save a itnsVillage.
     *
     * @param itnsVillage the entity to save.
     * @return the persisted entity.
     */
    ItnsVillage save(ItnsVillage itnsVillage);

    /**
     * Updates a itnsVillage.
     *
     * @param itnsVillage the entity to update.
     * @return the persisted entity.
     */
    ItnsVillage update(ItnsVillage itnsVillage);

    /**
     * Partially updates a itnsVillage.
     *
     * @param itnsVillage the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItnsVillage> partialUpdate(ItnsVillage itnsVillage);

    /**
     * Get all the itnsVillages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItnsVillage> findAll(Pageable pageable);

    /**
     * Get all the itnsVillages with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItnsVillage> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" itnsVillage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItnsVillage> findOne(Long id);

    /**
     * Delete the "id" itnsVillage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
