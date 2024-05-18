package org.nmcpye.datarun.service;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ReviewTeam;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ReviewTeam}.
 */
public interface ReviewTeamService {
    /**
     * Save a reviewTeam.
     *
     * @param reviewTeam the entity to save.
     * @return the persisted entity.
     */
    ReviewTeam save(ReviewTeam reviewTeam);

    /**
     * Updates a reviewTeam.
     *
     * @param reviewTeam the entity to update.
     * @return the persisted entity.
     */
    ReviewTeam update(ReviewTeam reviewTeam);

    /**
     * Partially updates a reviewTeam.
     *
     * @param reviewTeam the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReviewTeam> partialUpdate(ReviewTeam reviewTeam);

    /**
     * Get all the reviewTeams.
     *
     * @return the list of entities.
     */
    List<ReviewTeam> findAll();

    /**
     * Get the "id" reviewTeam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReviewTeam> findOne(Long id);

    /**
     * Delete the "id" reviewTeam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
