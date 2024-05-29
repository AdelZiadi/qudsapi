package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSessions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ChvSessions}.
 */
public interface ChvSessionsService {
    /**
     * Save a chvSessions.
     *
     * @param chvSessions the entity to save.
     * @return the persisted entity.
     */
    ChvSessions save(ChvSessions chvSessions);

    /**
     * Updates a chvSessions.
     *
     * @param chvSessions the entity to update.
     * @return the persisted entity.
     */
    ChvSessions update(ChvSessions chvSessions);

    /**
     * Partially updates a chvSessions.
     *
     * @param chvSessions the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChvSessions> partialUpdate(ChvSessions chvSessions);

    /**
     * Get all the chvSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvSessions> findAll(Pageable pageable);

    /**
     * Get all the chvSessions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvSessions> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" chvSessions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChvSessions> findOne(Long id);

    /**
     * Delete the "id" chvSessions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
