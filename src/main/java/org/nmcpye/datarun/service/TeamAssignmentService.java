package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.TeamAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.TeamAssignment}.
 */
public interface TeamAssignmentService {
    /**
     * Save a teamAssignment.
     *
     * @param teamAssignment the entity to save.
     * @return the persisted entity.
     */
    TeamAssignment save(TeamAssignment teamAssignment);

    /**
     * Updates a teamAssignment.
     *
     * @param teamAssignment the entity to update.
     * @return the persisted entity.
     */
    TeamAssignment update(TeamAssignment teamAssignment);

    /**
     * Partially updates a teamAssignment.
     *
     * @param teamAssignment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TeamAssignment> partialUpdate(TeamAssignment teamAssignment);

    /**
     * Get all the teamAssignments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TeamAssignment> findAll(Pageable pageable);

    /**
     * Get all the teamAssignments with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TeamAssignment> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" teamAssignment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeamAssignment> findOne(Long id);

    /**
     * Delete the "id" teamAssignment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
