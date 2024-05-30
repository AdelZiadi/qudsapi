package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ChvSession}.
 */
public interface ChvSessionService {
    /**
     * Save a chvSession.
     *
     * @param chvSession the entity to save.
     * @return the persisted entity.
     */
    ChvSession save(ChvSession chvSession);

    /**
     * Updates a chvSession.
     *
     * @param chvSession the entity to update.
     * @return the persisted entity.
     */
    ChvSession update(ChvSession chvSession);

    /**
     * Partially updates a chvSession.
     *
     * @param chvSession the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChvSession> partialUpdate(ChvSession chvSession);

    /**
     * Get all the chvSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvSession> findAll(Pageable pageable);

    /**
     * Get all the chvSessions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvSession> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" chvSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChvSession> findOne(Long id);

    /**
     * Delete the "id" chvSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
