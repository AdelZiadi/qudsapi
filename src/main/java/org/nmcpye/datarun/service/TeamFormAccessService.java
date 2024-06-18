package org.nmcpye.datarun.service;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.TeamFormAccess;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.TeamFormAccess}.
 */
public interface TeamFormAccessService {
    /**
     * Save a teamFormAccess.
     *
     * @param teamFormAccess the entity to save.
     * @return the persisted entity.
     */
    TeamFormAccess save(TeamFormAccess teamFormAccess);

    /**
     * Updates a teamFormAccess.
     *
     * @param teamFormAccess the entity to update.
     * @return the persisted entity.
     */
    TeamFormAccess update(TeamFormAccess teamFormAccess);

    /**
     * Partially updates a teamFormAccess.
     *
     * @param teamFormAccess the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TeamFormAccess> partialUpdate(TeamFormAccess teamFormAccess);

    /**
     * Get all the teamFormAccesses.
     *
     * @return the list of entities.
     */
    List<TeamFormAccess> findAll();

    /**
     * Get the "id" teamFormAccess.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeamFormAccess> findOne(Long id);

    /**
     * Delete the "id" teamFormAccess.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
